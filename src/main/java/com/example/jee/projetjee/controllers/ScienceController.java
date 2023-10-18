package com.example.jee.projetjee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScienceController {

    @RequestMapping("/")
    String index(Model model){

        model.addAttribute("body", "fragments/accueil.html :: main");

        return "index";
    }
}
