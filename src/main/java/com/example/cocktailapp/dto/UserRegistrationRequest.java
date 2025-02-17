package com.example.cocktailapp.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
    // Optional team ID; the client may pass null if not assigning immediately.
    private Long teamId;
}
