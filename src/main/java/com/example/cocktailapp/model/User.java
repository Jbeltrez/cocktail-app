package com.example.cocktailapp.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    // Optionally, you can add team affiliation here later
}
