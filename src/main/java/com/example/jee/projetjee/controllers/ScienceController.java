package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.*;
import com.example.jee.projetjee.repositories.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import java.util.Optional;

@Controller
public class ScienceController {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ScienceRepository scienceRepository;
    private final CartRepository cartRepository;
    private final ScienceQuantityRepository scienceQuantityRepository;

    @Inject
    public ScienceController(@NotNull CategoryRepository categoryRepository, UserRepository userRepository, CartRepository cartRepository,
                             ScienceRepository scienceRepository,
                             ScienceQuantityRepository scienceQuantityRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.scienceRepository = scienceRepository;
        this.cartRepository = cartRepository;
        this.scienceQuantityRepository = scienceQuantityRepository;
    }

    @RequestMapping("/")
    String index(@NotNull Model model) {

        model.addAttribute("template", "home");

        return "home";
    }

    @RequestMapping({"/science", "/science/{catName}"})
    String science(@NotNull Model model, @PathVariable Optional<String> catName) {
        var categories = categoryRepository.findAll();

        Optional<Category> selectedCat =  getCategoryOrFirst(catName);

        model.addAttribute("categories", categories);
        model.addAttribute("selectedCat", selectedCat);
        model.addAttribute("template", "science");

        return "science";
    }

    @PostMapping("/science/command/{id}")
    RedirectView command(Model model, @PathVariable long id, @RequestParam int quantity){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof User user){
            Science science = scienceRepository.findByIdScience(id);
            Cart cart = cartRepository.findByUser(user).stream().filter(c -> c.getDate() == null).findFirst().orElseGet(() -> {
                Cart c = new Cart();
                c.setUser(user);
                cartRepository.save(c);
                return c;
            });
            ScienceQuantity scienceQuantity = scienceQuantityRepository
                    .findById(new ScienceQuantity.Key(cart, science))
                    .orElseGet(()-> new ScienceQuantity(new ScienceQuantity.Key(cart, science), 0));

            if (scienceQuantity.getQuantity() + quantity <= science.getStock()){
                scienceQuantity.setQuantity(scienceQuantity.getQuantity() + quantity);
                scienceQuantityRepository.save(scienceQuantity);
            }
            else {
                model.addAttribute("notification", "Not enough stock");
            }
        }
        return new RedirectView("/science");
    }

    @RequestMapping("/cart")
    String cart(@NotNull Model model){

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof User user){
            Optional<Cart> cartOptional = cartRepository.findByUser(user).stream().filter(c -> c.getDate() == null).findFirst();

            cartOptional.ifPresent(cart -> {
                model.addAttribute("cart", cart);
                var sumStream = cart.getScienceQuantities().stream().mapToDouble(scienceQuantity ->
                        scienceQuantity.getQuantity() * scienceQuantity.getKey().getScience().getPrice());
                model.addAttribute("sum", sumStream.sum());
            });
        }


        model.addAttribute("template", "cart");
        return "cart";
    }

    @RequestMapping("/cart/{id}/{action}")
    RedirectView cartAction(Model model, @PathVariable String action, @PathVariable long id){

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof User user){
            Optional<Cart> cartOptional = cartRepository.findByUser(user).stream().filter(c -> c.getDate() == null).findFirst();

            if (cartOptional.isPresent()){
                var scienceQuantityOptional = cartOptional.get().getScienceQuantities().stream().filter(scienceQuantity1 -> scienceQuantity1.getKey().getScience().getIdScience() == id).findFirst();
                if (scienceQuantityOptional.isPresent()){
                    ScienceQuantity scienceQuantity = scienceQuantityOptional.get();
                    makeAction(action, scienceQuantity);
                }
            }
        }


        return new RedirectView("/cart");
    }

    private void makeAction(@NotNull String action, ScienceQuantity scienceQuantity) {
        switch (action){
            case "decrement":
                if (scienceQuantity.getQuantity() > 1){
                    scienceQuantity.setQuantity(scienceQuantity.getQuantity() - 1);
                    scienceQuantityRepository.save(scienceQuantity);
                }
                else {
                    scienceQuantityRepository.delete(scienceQuantity);
                }
                break;
            case "increment":
                if (scienceQuantity.getQuantity() + 1 <= scienceQuantity.getKey().getScience().getStock()){
                    scienceQuantity.setQuantity(scienceQuantity.getQuantity() + 1);
                    scienceQuantityRepository.save(scienceQuantity);
                }
                break;
            case "remove":
                scienceQuantityRepository.delete(scienceQuantity);
                break;
            default:
                break;
        }
    }


    private Optional<Category> getCategoryOrFirst(@NotNull Optional<String> catName) {
        Optional<Category> selectedCat;
        if (catName.isPresent()) {
            selectedCat = categoryRepository.findByName(catName.get());
        }
        else {
            selectedCat = categoryRepository.findFirstByOrderByNameDesc();
        }
        return selectedCat;
    }

}
