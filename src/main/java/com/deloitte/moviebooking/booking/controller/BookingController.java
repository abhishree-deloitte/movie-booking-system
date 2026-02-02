package com.deloitte.moviebooking.booking.controller;

import com.deloitte.moviebooking.booking.dto.CreateBookingRequest;
import com.deloitte.moviebooking.booking.model.Booking;
import com.deloitte.moviebooking.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for booking-related APIs.
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Creates a new booking for the authenticated user.
     * User explicitly selects seats on UI.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Booking createBooking(
            @Valid @RequestBody CreateBookingRequest request
    ) {
        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return bookingService.createBooking(
                userId,
                request.getShowId(),
                request.getSeatIds()
        );
    }

    /**
     * Fetch booking details.
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{bookingId}")
    public Booking getBooking(@PathVariable String bookingId) {

        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return bookingService.getBookingById(bookingId, userId);
    }

    /**
     * Cancels a booking and releases seats.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{bookingId}/cancel")
    public Booking cancelBooking(@PathVariable String bookingId) {

        String userId = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return bookingService.cancelBooking(bookingId, userId);
    }
}
