package com.example.cocktailapp.service;

import com.example.cocktailapp.dto.CategoryDTO;
import com.example.cocktailapp.dto.DrinkDTO;
import com.example.cocktailapp.dto.MenuDTO;
import com.example.cocktailapp.dto.TeamUpdateDTO;
import com.example.cocktailapp.dto.TeamDTO;
import com.example.cocktailapp.model.Category;
import com.example.cocktailapp.model.Drink;
import com.example.cocktailapp.model.Menu;
import com.example.cocktailapp.model.Team;
import com.example.cocktailapp.model.TeamUpdate;
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
        // This method is used when mapping a standalone Drink.
        CategoryDTO catDto = new CategoryDTO();
        catDto.setId(drink.getCategory().getId());
        catDto.setName(drink.getCategory().getName());
        // Not setting drinks in CategoryDTO to avoid recursion.
        dto.setCategory(catDto);

        return dto;
    }

    // Helper method: maps a Drink to DrinkDTO without setting its category
    // to avoid circular references when mapping within a Category.
    public DrinkDTO mapToDrinkDTOWithoutCategory(Drink drink) {
        DrinkDTO dto = new DrinkDTO();
        dto.setId(drink.getId());
        dto.setName(drink.getName());
        dto.setDescription(drink.getDescription());
        // Do not set the category field here.
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

    public TeamDTO mapToTeamDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        return dto;
    }

    public TeamUpdateDTO mapToTeamUpdateDTO(TeamUpdate update) {
        TeamUpdateDTO dto = new TeamUpdateDTO();
        dto.setId(update.getId());
        dto.setContent(update.getContent());
        dto.setTimestamp(update.getTimestamp());
        if (update.getTeam() != null) {
            dto.setTeamId(update.getTeam().getId());
        }
        return dto;
    }

    // New method to map Category to CategoryDTO including its drinks without recursion.
    public CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        if (category.getDrinks() != null) {
            Set<DrinkDTO> drinkDTOs = category.getDrinks().stream()
                    .map(this::mapToDrinkDTOWithoutCategory)
                    .collect(Collectors.toSet());
            dto.setDrinks(drinkDTOs);
        }
        return dto;
    }
}
