
package com.example.cocktailapp.repository;

import com.example.cocktailapp.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    // Additional query methods (if needed) go here.
}