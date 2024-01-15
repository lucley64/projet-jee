package com.example.jee.projetjee.dto;

import com.example.jee.projetjee.data.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

    public RoleDto(@NotNull Role role) {
        this.idRole = role.getIdRole();
        this.name = role.getName();
    }

    long idRole;
    private String name;


}
