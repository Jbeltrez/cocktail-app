package com.example.cocktailapp.service;

import com.example.cocktailapp.dto.CategoryDTO;
import com.example.cocktailapp.dto.DrinkDTO;
import com.example.cocktailapp.model.Drink;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    public DrinkDTO mapToDrinkDTO(Drink drink) {
        DrinkDTO dto = new DrinkDTO();
        dto.setId(drink.getId());
        dto.setName(drink.getName());
        dto.setDescription(drink.getDescription());

        // Map associated Category to its DTO.
        CategoryDTO catDto = new CategoryDTO();
        catDto.setId(drink.getCategory().getId());
        catDto.setName(drink.getCategory().getName());
        dto.setCategory(catDto);

        return dto;
    }
}
