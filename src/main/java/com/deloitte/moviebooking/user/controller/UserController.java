package com.deloitte.moviebooking.user.controller;

import com.deloitte.moviebooking.booking.model.Booking;
import com.deloitte.moviebooking.booking.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController exposes user profile related APIs.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final BookingService bookingService;

    public UserController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Fetch bookings of the authenticated user.
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me/bookings")
    public List<Booking> myBookings() {

        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return bookingService.getBookingsForUser(userId);
    }
}
