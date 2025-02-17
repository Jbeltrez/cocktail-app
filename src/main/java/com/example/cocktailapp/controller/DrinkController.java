package com.example.cocktailapp.controller;

import com.example.cocktailapp.dto.DrinkDTO;
import com.example.cocktailapp.dto.DrinkInputDTO;
import com.example.cocktailapp.model.Category;
import com.example.cocktailapp.model.Drink;
import com.example.cocktailapp.repository.CategoryRepository;
import com.example.cocktailapp.repository.DrinkRepository;
import com.example.cocktailapp.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // GET endpoint to retrieve all drinks
    @GetMapping
    public List<DrinkDTO> getAllDrinks() {
        List<Drink> drinks = drinkRepository.findAll();
        return drinks.stream()
                .map(mappingService::mapToDrinkDTO)
                .collect(Collectors.toList());
    }

    // POST endpoint to create a new drink
    @PostMapping
    public DrinkDTO createDrink(@RequestBody DrinkInputDTO drinkInput) {
        Drink drink = new Drink();
        drink.setName(drinkInput.getName());
        drink.setDescription(drinkInput.getDescription());

        Optional<Category> categoryOpt = categoryRepository.findById(drinkInput.getCategoryId());
        if (categoryOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        drink.setCategory(categoryOpt.get());

        Drink savedDrink = drinkRepository.save(drink);
        return mappingService.mapToDrinkDTO(savedDrink);
    }

    // PUT endpoint to update an existing drink
    @PutMapping("/{id}")
    public DrinkDTO updateDrink(@PathVariable Long id, @RequestBody DrinkInputDTO drinkInput) {
        Optional<Drink> optionalDrink = drinkRepository.findById(id);
        if (optionalDrink.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drink not found");
        }
        Drink drink = optionalDrink.get();
        drink.setName(drinkInput.getName());
        drink.setDescription(drinkInput.getDescription());

        Optional<Category> categoryOpt = categoryRepository.findById(drinkInput.getCategoryId());
        if (categoryOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        drink.setCategory(categoryOpt.get());

        Drink updatedDrink = drinkRepository.save(drink);
        return mappingService.mapToDrinkDTO(updatedDrink);
    }

    // DELETE endpoint to delete a drink by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        if (!drinkRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drink not found");
        }
        drinkRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public DrinkDTO getDrinkById(@PathVariable Long id) {
        return drinkRepository.findById(id)
                .map(mappingService::mapToDrinkDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drink not found"));
    }

}
