package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.Category;
import com.example.jee.projetjee.repositories.CategoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.Optional;

@Controller
public class ScienceController {

    private final CategoryRepository categoryRepository;

    @Inject
    public ScienceController(@NotNull CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
