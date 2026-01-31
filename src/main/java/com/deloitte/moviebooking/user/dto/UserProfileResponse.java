package com.deloitte.moviebooking.user.dto;

/**
 * UserProfileResponse represents user profile data
 * returned to the client.
 */
public class UserProfileResponse {

    public String userId;
    public String username;
    public String email;

    public UserProfileResponse(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
