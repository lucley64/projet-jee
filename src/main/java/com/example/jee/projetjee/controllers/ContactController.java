package com.example.jee.projetjee.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    @RequestMapping("/contact")
    String contact(@NotNull Model model) {

        model.addAttribute("template", "contact");

        return "contact";
    }
}
