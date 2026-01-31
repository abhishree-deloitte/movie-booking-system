package com.deloitte.moviebooking.user.controller;

import com.deloitte.moviebooking.booking.model.Booking;
import com.deloitte.moviebooking.booking.service.BookingService;
import com.deloitte.moviebooking.user.dto.UpdateEmailRequest;
import com.deloitte.moviebooking.user.dto.UpdateUsernameRequest;
import com.deloitte.moviebooking.user.dto.UserProfileResponse;
import com.deloitte.moviebooking.user.model.User;
import com.deloitte.moviebooking.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController exposes user profile related APIs.
 */
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    public UserController(UserService userService,
                          BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    /**
     * Get authenticated user's profile.
     */
    @GetMapping("/me")
    public UserProfileResponse getMyProfile() {

        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        User user = userService.getUserById(userId);

        return new UserProfileResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    /**
     * Fetch bookings of authenticated user.
     */
    @GetMapping("/me/bookings")
    public List<Booking> myBookings() {

        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return bookingService.getBookingsForUser(userId);
    }

    /**
     * Update username.
     */
    @PutMapping("/me/username")
    public UserProfileResponse updateUsername(
            @Valid @RequestBody UpdateUsernameRequest request
    ) {
        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        User user = userService.updateUsername(userId, request.username);

        return new UserProfileResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    /**
     * Update email.
     */
    @PutMapping("/me/email")
    public UserProfileResponse updateEmail(
            @Valid @RequestBody UpdateEmailRequest request
    ) {
        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        User user = userService.updateEmail(userId, request.email);

        return new UserProfileResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
