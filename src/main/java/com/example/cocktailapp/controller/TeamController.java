// src/main/java/com/example/cocktailapp/controller/TeamController.java
package com.example.cocktailapp.controller;

import com.example.cocktailapp.model.Team;
import com.example.cocktailapp.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
