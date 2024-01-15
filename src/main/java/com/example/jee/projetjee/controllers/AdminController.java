package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.Category;
import com.example.jee.projetjee.data.Role;
import com.example.jee.projetjee.data.Science;
import com.example.jee.projetjee.data.User;
import com.example.jee.projetjee.dto.CategoryDto;
import com.example.jee.projetjee.dto.RoleDto;
import com.example.jee.projetjee.dto.ScienceDto;
import com.example.jee.projetjee.dto.UserDto;
import com.example.jee.projetjee.repositories.CategoryRepository;
import com.example.jee.projetjee.repositories.RoleRepository;
import com.example.jee.projetjee.repositories.ScienceRepository;
import com.example.jee.projetjee.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserRepository userRepository;
    RoleRepository roleRepository;
    ScienceRepository scienceRepository;
    CategoryRepository categoryRepository;
    PasswordEncoder passwordEncoder;

    @Inject
    public AdminController(UserRepository userRepository, RoleRepository roleRepository, ScienceRepository scienceRepository, CategoryRepository categoryRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.scienceRepository = scienceRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("")
    String adminBase(@NotNull Model model) {

        templateAttr(model);

        return "admin/adminPage";
    }

    @RequestMapping("/user")
    String readUsers(@NotNull Model model) {

        var data = userRepository.findAll();

        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/user";
    }

    @GetMapping("/user/{id}")
    String readUsers(@NotNull Model model, @PathVariable long id) {

        var user = userRepository.findById(id);
        var roles = roleRepository.findAll();
        UserDto userDto = user.map(UserDto::new).orElseGet(UserDto::new);


        model.addAttribute("userDto", userDto);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        templateAttr(model);
        return "admin/userForm";
    }

    @PostMapping("/user/{id}")
    RedirectView modifyAddUser(@NotNull Model model, @ModelAttribute @NotNull UserDto userDto, @PathVariable String id) {

        Optional<User> optionalUser = userRepository.findById(userDto.getIdUser());

        User user = optionalUser.orElseGet(User::new);

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setRoles(userDto.getRoles());
        user.setAccountNonExpired(userDto.isAccountNonExpired());
        user.setAccountNonLocked(userDto.isAccountNonLocked());
        user.setCredentialsNonExpired(userDto.isCredentialsNonExpired());
        user.setEnabled(userDto.isEnabled());

        if (optionalUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        userRepository.save(user);

        return new RedirectView("/admin/user");
    }

    @RequestMapping("/role")
    String readRoles(@NotNull Model model) {

        var data = roleRepository.findAll();
        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/role";
    }

    @GetMapping("/role/{id}")
    String readRoles(@NotNull Model model, @PathVariable long id) {

        var role = roleRepository.findById(id);
        RoleDto roleDto = role.map(RoleDto::new).orElseGet(RoleDto::new);


        model.addAttribute("roleDto", roleDto);
        model.addAttribute("role", role);
        templateAttr(model);
        return "admin/roleForm";
    }

    @PostMapping("/role/{id}")
    RedirectView modifyAddRole(@NotNull Model model, @ModelAttribute @NotNull RoleDto roleDto, @PathVariable String id) {

        Optional<Role> optionalRole = roleRepository.findById(roleDto.getIdRole());

        Role role = optionalRole.orElseGet(Role::new);

        role.setName(roleDto.getName());

        roleRepository.save(role);

        return new RedirectView("/admin/role");
    }

    @RequestMapping("/science")
    String readScience(@NotNull Model model) {

        var data = scienceRepository.findAll();
        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/science";
    }

    @GetMapping("/science/{id}")
    String readSciences(@NotNull Model model, @PathVariable long id) {

        var science = scienceRepository.findById(id);
        ScienceDto scienceDto = science.map(sci -> new ScienceDto(sci.getIdScience(), sci.getImage(), sci.getName(), sci.getPrice(), sci.getStock(), sci.getCategory())).orElseGet(ScienceDto::new);
        var categories = categoryRepository.findAll();

        model.addAttribute("scienceDto", scienceDto);
        model.addAttribute("science", science);
        model.addAttribute("categories", categories);
        templateAttr(model);
        return "admin/scienceForm";
    }

    @PostMapping("/science/{id}")
    RedirectView modifyAddScience(@NotNull Model model, @ModelAttribute @NotNull ScienceDto scienceDto, @PathVariable String id) {

        Optional<Science> optionalScience = scienceRepository.findById(scienceDto.getIdScience());

        Science science = optionalScience.orElseGet(Science::new);

        science.setImage(scienceDto.getImage());
        science.setName(scienceDto.getName());
        science.setPrice(scienceDto.getPrice());
        science.setStock(scienceDto.getStock());
        science.setCategory(scienceDto.getCategory());

        scienceRepository.save(science);

        return new RedirectView("/admin/science");
    }

    @RequestMapping("/category")
    String readCategories(@NotNull Model model) {

        var data = categoryRepository.findAll();
        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/category";
    }

    @GetMapping("/category/{id}")
    String readCategories(@NotNull Model model, @PathVariable long id) {

        var category = categoryRepository.findById(id);
        CategoryDto categoryDto = category.map(cat -> new CategoryDto(cat.getIdCategory(), cat.getName())).orElseGet(CategoryDto::new);


        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("category", category);
        templateAttr(model);
        return "admin/categoryForm";
    }

    @PostMapping("/category/{id}")
    RedirectView modifyAddCategory(@NotNull Model model, @ModelAttribute @NotNull CategoryDto categoryDto, @PathVariable String id) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getIdCategory());

        Category category = optionalCategory.orElseGet(Category::new);

        category.setName(categoryDto.getName());

        categoryRepository.save(category);

        return new RedirectView("/admin/category");
    }


    @PostMapping("/{table}/del/{id}")
    RedirectView delete(@NotNull Model model, @PathVariable @NotNull String table, @PathVariable Long id) {
        CrudRepository<?, Long> repository = switch (table) {
            case "user" -> userRepository;
            case "role" -> roleRepository;
            case "science" -> scienceRepository;
            case "category" -> categoryRepository;
            default -> null;
        };

        if (repository != null) {
            repository.deleteById(id);
            model.addAttribute("notification", "Delete success");
        } else {
            model.addAttribute("notification", "Delete failed");
        }

        return new RedirectView("/admin/" + table);
    }

    private static void templateAttr(@NotNull Model model) {
        model.addAttribute("template", "admin");
    }
}
