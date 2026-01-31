package com.deloitte.moviebooking.user.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request to update username.
 */
public class UpdateUsernameRequest {

    @NotBlank(message = "Username cannot be empty")
    public String username;
}
