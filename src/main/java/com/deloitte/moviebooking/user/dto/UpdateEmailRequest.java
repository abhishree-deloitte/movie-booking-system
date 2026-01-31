package com.deloitte.moviebooking.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request to update email.
 */
public class UpdateEmailRequest {

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    public String email;
}
