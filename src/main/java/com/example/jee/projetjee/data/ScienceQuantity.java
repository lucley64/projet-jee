package com.example.jee.projetjee.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScienceQuantity {

    @EmbeddedId
    Key key;

    @Column
    int quantity = 0;


    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Key implements Serializable{

        @ManyToOne
        Cart cart;

        @ManyToOne
        Science science;

    }
}
