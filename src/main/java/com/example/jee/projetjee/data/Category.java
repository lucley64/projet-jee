package com.example.jee.projetjee.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCategory;

    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = Science.class, mappedBy = "category")
    private Set<Science> sciences;
}
