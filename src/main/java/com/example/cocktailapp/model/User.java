package com.example.cocktailapp.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Associate a user with a team (optional)
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
