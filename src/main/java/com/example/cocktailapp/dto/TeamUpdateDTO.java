package com.example.cocktailapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TeamUpdateDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    // We expose the team ID (and optionally, team name) in the DTO
    private Long teamId;
    // Optionally, you could add a teamName field if desired.
}
