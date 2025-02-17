package com.example.cocktailapp.dto;

import lombok.Data;
import java.util.Set;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Set<DrinkDTO> drinks;  // Include associated drinks if desired.
}
