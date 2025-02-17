package com.example.cocktailapp.controller;

import com.example.cocktailapp.model.Menu;
import com.example.cocktailapp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    // GET endpoint to retrieve all menus
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    // GET endpoint to retrieve a single menu by ID
    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
    }

    // POST endpoint to create a new menu
    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    // PUT endpoint to update a menu's name
    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        menu.setName(menuDetails.getName());
        return menuRepository.save(menu);
    }

    // DELETE endpoint to delete a menu
    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        menuRepository.delete(menu);
    }
}
