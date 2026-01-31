package com.deloitte.moviebooking.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request body for user login.
 */
public class LoginRequest {

    @NotBlank(message = "Username cannot be empty")
    public String username;

    @NotBlank(message = "Password cannot be empty")
    public String password;
}
