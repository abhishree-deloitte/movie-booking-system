package com.deloitte.moviebooking.theatre.screen.seat.controller;

import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import com.deloitte.moviebooking.theatre.screen.seat.model.SeatType;
import com.deloitte.moviebooking.theatre.screen.seat.service.SeatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for seat-related APIs.
 *
 * Seats are always accessed in the context of a screen.
 */
@RestController
@RequestMapping("/screens/{screenId}/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /**
     * Creates a new seat under a screen.
     * Allowed only for ADMIN.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Seat createSeat(
            @PathVariable String screenId,
            @RequestParam String row,
            @RequestParam int number,
            @RequestParam SeatType type
    ) {
        return seatService.createSeat(screenId, row, number, type);
    }

    /**
     * Fetches all seats for a screen.
     * Allowed for USER and ADMIN.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Seat> getSeats(@PathVariable String screenId) {
        return seatService.getSeatsByScreen(screenId);
    }
}
