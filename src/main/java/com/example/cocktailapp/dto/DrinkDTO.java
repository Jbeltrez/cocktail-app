package com.example.cocktailapp.dto;

import com.example.cocktailapp.dto.CategoryDTO;
import lombok.Data;

@Data
public class DrinkDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryDTO category; // single CategoryDTO, not a list
}
