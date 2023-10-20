package com.example.jee.projetjee.configurations;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity security) throws Exception {


        return security
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/", "/science", "/contact", "/css/**", "/img/**", "/js/**").permitAll().anyRequest().authenticated()
                )
                .formLogin(form ->
                        form.loginPage("/login").permitAll()
                )
                .logout(LogoutConfigurer::permitAll).build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
