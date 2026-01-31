package com.deloitte.moviebooking.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request body for user registration.
 */
public class RegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    public String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    public String email;

    @NotBlank(message = "Password cannot be empty")
    public String password;

    @NotBlank(message = "Role cannot be empty")
    public String role; // USER or ADMIN
}
