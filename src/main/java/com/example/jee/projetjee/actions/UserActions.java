package com.example.jee.projetjee.actions;

import com.example.jee.projetjee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserActions {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    public void updateUserEmail(long id, String email){

    }
}
