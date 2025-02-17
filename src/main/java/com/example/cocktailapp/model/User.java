package com.example.cocktailapp.model;

import lombok.Data;
import javax.persistence.*;
// I WILL NOW BEGIN MAKING THE ASSOCIATION WITH USERS AND TEAMS,
// THIS COULD GET COMPLICATED SO I AM LEAVING THIS COMMENT HERE BEFORE I PROCEED
// WILL COMMIT THIS COMMENT
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
