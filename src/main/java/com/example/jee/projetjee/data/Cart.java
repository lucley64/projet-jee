package com.example.jee.projetjee.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Nullable
    private Date date;

    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "key.cart")
    Set<ScienceQuantity> scienceQuantities;
}
