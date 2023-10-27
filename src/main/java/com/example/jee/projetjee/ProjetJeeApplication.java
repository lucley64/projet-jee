package com.example.jee.projetjee;

import com.example.jee.projetjee.data.Role;
import com.example.jee.projetjee.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetJeeApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepository){
        return args -> {
            var admin = roleRepository.findByName("ROLE_ADMINISTRATOR");
            if (admin.isEmpty()){
                Role role = new Role();
                role.setName("ADMINISTRATOR");
                roleRepository.save(role);
            }
            var user = roleRepository.findByName("ROLE_USER");
            if (user.isEmpty()){
                Role role = new Role();
                role.setName("USER");
                roleRepository.save(role);
            }
        };
    }

}
