package com.example.jee.projetjee.dto;

import com.example.jee.projetjee.data.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScienceDto {

    long idScience;
    private String image;
    private String name;
    private double price;
    private int stock;
    private Category category;


}
