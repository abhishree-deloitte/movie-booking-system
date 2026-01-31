package com.deloitte.moviebooking.auth.dto;

/**
 * Request body for user login.
 * Used by /auth/login API.
 */
public class LoginRequest {

    public String username;
    public String password;
}
