package com.example.cocktailapp.controller;

import com.example.cocktailapp.dto.TeamUpdateDTO;
import com.example.cocktailapp.model.Team;
import com.example.cocktailapp.model.TeamUpdate;
import com.example.cocktailapp.repository.TeamRepository;
import com.example.cocktailapp.repository.TeamUpdateRepository;
import com.example.cocktailapp.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/team-updates")
public class TeamUpdateController {

    @Autowired
    private TeamUpdateRepository teamUpdateRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MappingService mappingService;

    // GET endpoint: Retrieve updates for a specific team by team ID provided in the URL
    @GetMapping("/team/{teamId}")
    public List<TeamUpdateDTO> getUpdatesByTeam(@PathVariable Long teamId) {
        List<TeamUpdate> updates = teamUpdateRepository.findByTeamId(teamId);
        return updates.stream()
                .map(mappingService::mapToTeamUpdateDTO)
                .collect(Collectors.toList());
    }

    // POST endpoint: Create a new team update; client must supply the teamId in the DTO.
    @PostMapping
    public TeamUpdateDTO createTeamUpdate(@RequestBody TeamUpdateDTO updateDTO) {
        Long teamId = updateDTO.getTeamId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        TeamUpdate update = new TeamUpdate();
        update.setContent(updateDTO.getContent());
        update.setTeam(team);
        TeamUpdate savedUpdate = teamUpdateRepository.save(update);
        return mappingService.mapToTeamUpdateDTO(savedUpdate);
    }

    // DELETE endpoint: Delete a team update by ID
    @DeleteMapping("/{id}")
    public void deleteTeamUpdate(@PathVariable Long id) {
        if (!teamUpdateRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team update not found");
        }
        teamUpdateRepository.deleteById(id);
    }
}
