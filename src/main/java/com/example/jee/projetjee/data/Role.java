package com.example.jee.projetjee.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

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
        Assert.isTrue(!name.startsWith("ROLE_"),
                () -> name + " cannot start with ROLE_ (it is automatically added)");
        this.name = "ROLE_" + name;
    }

    @Override
    public String getAuthority() {
        return name;
    }


}
