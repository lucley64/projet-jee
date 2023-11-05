package com.example.jee.projetjee;

import com.example.jee.projetjee.data.Role;
import com.example.jee.projetjee.data.User;
import com.example.jee.projetjee.repositories.RoleRepository;
import com.example.jee.projetjee.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ProjetJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetJeeApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepository,
                                  UserRepository userRepository,
                                  PasswordEncoder passwordEncoder){
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
            var userAdmin = userRepository.findByUsername("admin");
            admin = roleRepository.findByName("ROLE_ADMINISTRATOR");
            if (userAdmin.isEmpty() && admin.isPresent()){
                User user1 = new User();
                user1.setUsername("admin");
                user1.setEmail("admin");
                user1.setPassword(passwordEncoder.encode("admin"));
                user1.setRoles(Set.of(admin.get()));
                userRepository.save(user1);

            }
        };
    }

}
