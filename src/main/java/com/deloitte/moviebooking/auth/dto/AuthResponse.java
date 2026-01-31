package com.deloitte.moviebooking.auth.dto;

/**
 * Response returned after successful authentication.
 * Contains JWT token.
 */
public class AuthResponse {

    public String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
