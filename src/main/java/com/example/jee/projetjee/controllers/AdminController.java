package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.User;
import com.example.jee.projetjee.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class AdminController {

    @RequestMapping("/adminPage")
    String adminBase(Model model){

        // model.addAttribute("template", "adminPage");
        return "admin/adminPage";
    }

    private final UserRepository userRepository ;

    @Inject
    public AdminController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @GetMapping("admin/adminPage")
    private UserRepository doGet(String username, String mail){
        User u = new User();
        u.setUsername(username);
        u.setEmail(mail);
        userRepository.save(u);
        return userRepository;
    }

/*    @putMapping("/adminPage")
        private UserRepository doPut(long id,
*/
/*
    @PostMapping

    @DeleteMapping
    */
}
