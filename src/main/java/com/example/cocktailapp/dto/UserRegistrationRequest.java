package com.example.cocktailapp.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
    private Long teamId;
}
