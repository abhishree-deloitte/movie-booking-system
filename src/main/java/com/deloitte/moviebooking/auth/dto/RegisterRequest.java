package com.deloitte.moviebooking.auth.dto;

/**
 * Request body for user registration.
 * Used by /auth/register API.
 */
public class RegisterRequest {

    public String username;
    public String email;
    public String password;
    public String role; // USER or ADMIN
}
