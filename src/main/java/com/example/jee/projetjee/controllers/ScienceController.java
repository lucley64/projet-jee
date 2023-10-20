package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.Category;
import com.example.jee.projetjee.data.Science;
import com.example.jee.projetjee.repositories.ScienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

public class ScienceController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ScienceRepository scienceRepository;

    @RequestMapping("/")
    String index(Model model) {

        model.addAttribute("content", "fragments/accueil :: main");

        return "index";
    }

}
