package com.example.cocktailapp.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private Long teamId;

    public JwtResponse(String token, Long teamId) {
        this.token = token;
        this.teamId = teamId;
    }
}

