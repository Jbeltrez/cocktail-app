package com.example.cocktailapp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private Long teamId; // or a nested TeamDTO if you want more details
}
