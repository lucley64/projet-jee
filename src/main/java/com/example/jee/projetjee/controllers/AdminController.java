package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.dto.UserDto;
import com.example.jee.projetjee.repositories.CategoryRepository;
import com.example.jee.projetjee.repositories.RoleRepository;
import com.example.jee.projetjee.repositories.ScienceRepository;
import com.example.jee.projetjee.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class AdminController {

    UserRepository userRepository;
    RoleRepository roleRepository;
    ScienceRepository scienceRepository;
    CategoryRepository categoryRepository;

    @Inject
    public AdminController(UserRepository userRepository, RoleRepository roleRepository, ScienceRepository scienceRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.scienceRepository = scienceRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/admin")
    String adminBase(@NotNull Model model) {

        templateAttr(model);

        return "admin/adminPage";
    }

    @RequestMapping("/admin/user")
    String readUsers(@NotNull Model model) {

        var data = userRepository.findAll();

        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/user";
    }

    @GetMapping(value = "/admin/user/{id}")
    String readUsers(@NotNull Model model, @PathVariable long id) {

        var user = userRepository.findById(id);
        var roles = roleRepository.findAll();
        UserDto userDto = new UserDto();

        if (user.isPresent()){
            userDto.setIdUser(user.get().getIdUser());
            userDto.setEmail(user.get().getEmail());
            userDto.setUsername(user.get().getUsername());
            userDto.setPassword(user.get().getPassword());
            userDto.setRoles(user.get().getRoles());
            userDto.setAccountNonExpired(user.get().isAccountNonExpired());
            userDto.setAccountNonLocked(user.get().isAccountNonLocked());
            userDto.setCredentialsNonExpired(user.get().isCredentialsNonExpired());
            userDto.setEnabled(user.get().isEnabled());
        }

        model.addAttribute("userDto", userDto);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        templateAttr(model);
        return "admin/userForm";
    }

    @RequestMapping("/admin/role")
    String readRoles(@NotNull Model model) {

        var data = roleRepository.findAll();
        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/adminPage"; //TODO
    }

    @RequestMapping("/admin/science")
    String readScience(@NotNull Model model) {

        var data = scienceRepository.findAll();
        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/adminPage"; //TODO
    }

    @RequestMapping("/admin/category")
    String readCategories(@NotNull Model model) {

        var data = categoryRepository.findAll();
        model.addAttribute("data", data);
        templateAttr(model);
        return "admin/adminPage"; //TODO
    }


    private static void templateAttr(@NotNull Model model) {
        model.addAttribute("template", "admin");
    }
}
