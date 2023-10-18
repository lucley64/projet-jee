package com.example.jee.projetjee.controllers;

import com.example.jee.projetjee.data.User;
import com.example.jee.projetjee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    public void createUser(String email, String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedP = encoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedP);
        user.setUsername(username);
        userRepository.save(user);
    }
}

