package com.example.cocktailapp.dto;

import lombok.Data;
import java.util.Set;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    // Include associated drinks without their category to avoid recursion.
    private Set<DrinkDTO> drinks;
}
