package com.example.cocktailapp.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
