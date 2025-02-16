package com.example.cocktailapp.service;

import com.example.cocktailapp.model.Category;
import com.example.cocktailapp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        List<String> categoryNames = List.of("Beer", "Wine", "Cocktail", "Martini", "Shaken", "Stirred", "Hot", "Non-Alcoholic");

        for (String name : categoryNames) {
            if (!categoryRepository.existsByName(name)) {
                categoryRepository.save(new Category(null, name));
            }
        }
    }
}
