package com.example.jee.projetjee.data;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Carts {
    @Id
    @GeneratedValue
    private long id;

    @Column
    @Nullable
    private Date date;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Science> sciences;
}
