package com.example.jee.projetjee.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Category {

    @Id
    private long idCategory;

    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = Science.class, mappedBy = "category")
    private Set<Science> sciences;
}
