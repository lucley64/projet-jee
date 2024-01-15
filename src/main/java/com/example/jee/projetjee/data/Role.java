package com.example.jee.projetjee.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idRole;

    @Column(unique = true)
    private String name;

    public void setName(@NotNull String name) {
        this.name = name.startsWith("ROLE_") ? name : "ROLE_" + name;
    }

    @Override
    public String getAuthority() {
        return name;
    }


}
