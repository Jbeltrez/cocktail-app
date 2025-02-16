package com.example.cocktailapp.dto;

import lombok.Data;

@Data
public class DrinkInputDTO {
    private String name;
    private String description;
    private Long categoryId;
}
