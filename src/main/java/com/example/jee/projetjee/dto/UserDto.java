package com.example.jee.projetjee.dto;

import com.example.jee.projetjee.data.Role;
import com.example.jee.projetjee.data.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    public UserDto(@NotNull User user) {
        this.idUser = user.getIdUser();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.isAccountNonExpired = user.isAccountNonExpired();
        this.isAccountNonLocked = user.isAccountNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
    }

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
