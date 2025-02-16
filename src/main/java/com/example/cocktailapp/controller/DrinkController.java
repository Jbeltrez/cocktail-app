package com.example.cocktailapp.controller;

import com.example.cocktailapp.dto.DrinkDTO;
import com.example.cocktailapp.dto.DrinkInputDTO;
import com.example.cocktailapp.model.Category;
import com.example.cocktailapp.model.Drink;
import com.example.cocktailapp.repository.CategoryRepository;
import com.example.cocktailapp.repository.DrinkRepository;
import com.example.cocktailapp.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MappingService mappingService;

    @GetMapping
    public List<DrinkDTO> getAllDrinks() {
        List<Drink> drinks = drinkRepository.findAll();
        return drinks.stream()
                .map(mappingService::mapToDrinkDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DrinkDTO createDrink(@RequestBody DrinkInputDTO drinkInput) {
        Drink drink = new Drink();
        drink.setName(drinkInput.getName());
        drink.setDescription(drinkInput.getDescription());

        Optional<Category> categoryOpt = categoryRepository.findById(drinkInput.getCategoryId());
        if (categoryOpt.isPresent()) {
            drink.setCategory(categoryOpt.get());
        } else {
            throw new RuntimeException("Category not found");
        }

        Drink savedDrink = drinkRepository.save(drink);
        return mappingService.mapToDrinkDTO(savedDrink);
    }
}
