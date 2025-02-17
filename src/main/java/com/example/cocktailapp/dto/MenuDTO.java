package com.example.cocktailapp.dto;
// LEAVING THIS COMMENT HERE TO SOLIDIFY CURRENT STATE OF THE PROJECT, WILL COMMIT THIS
import lombok.Data;
import java.util.Set;

@Data
public class MenuDTO {
    private Long id;
    private String name;
    // Optionally, you can include a list of DrinkDTOs if you want to return the drinks associated with the menu.
    private Set<DrinkDTO> drinks;
}
