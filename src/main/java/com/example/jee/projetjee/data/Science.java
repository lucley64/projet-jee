package com.example.jee.projetjee.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Science {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idScience;

    @Column(unique = true)
    private String image;

    @Column(unique = true)
    private String name;

    @Column
    private double price;

    @Column
    private int stock;

    @ManyToOne(targetEntity = Category.class)
    private Category category;
}
