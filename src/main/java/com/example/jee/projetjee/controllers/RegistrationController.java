package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.User;
import com.example.jee.projetjee.dto.SignUpDto;
import com.example.jee.projetjee.repositories.RoleRepository;
import com.example.jee.projetjee.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Inject
    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public RedirectView registerUser(Model model, @ModelAttribute @NotNull SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            model.addAttribute("error", "Username already taken");
            return new RedirectView("/signup");
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            model.addAttribute("error", "Email already taken");
            return new RedirectView("/signup");
        }
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        var roles = roleRepository.findByName("ROLE_USER");
        roles.ifPresent(user::setRoles);
        userRepository.save(user);
        model.addAttribute("notification", "User is registered successfully!");
        return new RedirectView("/login");

    }

    @GetMapping("/signup")
    public String registerUser(@NotNull Model model){
        model.addAttribute("signupDto", new SignUpDto());
        return "signup";
    }
}
