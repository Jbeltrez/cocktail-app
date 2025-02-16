package com.example.cocktailapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Drink> drinks = new HashSet<>();

    // Custom two-argument constructor.
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
        this.drinks = new HashSet<>();
    }
}

