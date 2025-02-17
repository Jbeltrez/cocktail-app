package com.example.cocktailapp.service;

import com.example.cocktailapp.dto.CategoryDTO;
import com.example.cocktailapp.dto.DrinkDTO;
import com.example.cocktailapp.dto.MenuDTO;
import com.example.cocktailapp.model.Drink;
import com.example.cocktailapp.model.Menu;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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

    public MenuDTO mapToMenuDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        if (menu.getDrinks() != null) {
            Set<DrinkDTO> drinkDTOs = menu.getDrinks().stream()
                    .map(this::mapToDrinkDTO)
                    .collect(Collectors.toSet());
            dto.setDrinks(drinkDTOs);
        }
        return dto;
    }
}
