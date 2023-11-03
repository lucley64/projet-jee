package com.example.jee.projetjee.dto;

import com.example.jee.projetjee.data.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private long idUser;
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

}
