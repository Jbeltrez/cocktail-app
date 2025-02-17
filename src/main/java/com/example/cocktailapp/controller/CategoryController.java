package com.example.cocktailapp.controller;

import com.example.cocktailapp.dto.CategoryDTO;
import com.example.cocktailapp.model.Category;
import com.example.cocktailapp.repository.CategoryRepository;
import com.example.cocktailapp.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MappingService mappingService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(mappingService::mapToCategoryDTO)
                .collect(Collectors.toList());
    }
}
