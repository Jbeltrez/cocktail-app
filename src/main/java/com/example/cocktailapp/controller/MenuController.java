package com.example.cocktailapp.controller;

import com.example.cocktailapp.dto.MenuDTO;
import com.example.cocktailapp.model.Menu;
import com.example.cocktailapp.repository.MenuRepository;
import com.example.cocktailapp.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MappingService mappingService;

    // GET endpoint to retrieve all menus as DTOs
    @GetMapping
    public List<MenuDTO> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(mappingService::mapToMenuDTO)
                .collect(Collectors.toList());
    }

    // GET endpoint to retrieve a single menu by ID as a DTO
    @GetMapping("/{id}")
    public MenuDTO getMenuById(@PathVariable Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        return mappingService.mapToMenuDTO(menu);
    }

    // POST endpoint to create a new menu (returning DTO)
    @PostMapping
    public MenuDTO createMenu(@RequestBody Menu menu) {
        Menu savedMenu = menuRepository.save(menu);
        return mappingService.mapToMenuDTO(savedMenu);
    }

    // PUT endpoint to update a menu's name (returning DTO)
    @PutMapping("/{id}")
    public MenuDTO updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        menu.setName(menuDetails.getName());
        Menu updatedMenu = menuRepository.save(menu);
        return mappingService.mapToMenuDTO(updatedMenu);
    }

    // DELETE endpoint to delete a menu
    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        menuRepository.delete(menu);
    }
}
