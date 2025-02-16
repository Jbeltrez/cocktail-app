package com.example.cocktailapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // When you later create the Drink entity, you can add the relationship.
    // For now, we'll keep it simple.
    // @ManyToMany(mappedBy = "categories")
    // private Set<Drink> drinks = new HashSet<>();
}
