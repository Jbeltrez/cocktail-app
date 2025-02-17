package com.example.cocktailapp.service;

import com.example.cocktailapp.model.Team;
import com.example.cocktailapp.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSeeder implements CommandLineRunner {

    private final TeamRepository teamRepository;

    public TeamSeeder(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Preseed with a few default teams.
        List<String> teamNames = List.of("PerScholas", "PeopleShores", "Dominican Deli");
        for (String name : teamNames) {
            // Simple check to avoid duplicates – add existsByName method in TeamRepository if desired.
            boolean exists = teamRepository.findAll().stream()
                    .anyMatch(team -> team.getName().equalsIgnoreCase(name));
            if (!exists) {
                Team team = new Team();
                team.setName(name);
                teamRepository.save(team);
            }
        }
    }
}
