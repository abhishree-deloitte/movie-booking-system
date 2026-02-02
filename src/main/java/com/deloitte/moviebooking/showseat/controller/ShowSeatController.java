package com.deloitte.moviebooking.showseat.controller;
 
import com.deloitte.moviebooking.showseat.model.ShowSeat;
import com.deloitte.moviebooking.showseat.service.ShowSeatService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;

/**
 * REST controller for show seat-related APIs.
 *
 * Provides seat availability for a show.
 *
 * Only seats with status AVAILABLE are returned.
 * BOOKED seats are hidden from users.
 */
@RestController
@RequestMapping("/shows")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class ShowSeatController {

    private final ShowSeatService showSeatService;

    public ShowSeatController(ShowSeatService showSeatService) {
        this.showSeatService = showSeatService;
    }

    @GetMapping("/{showId}/seats")
    public List<ShowSeat> getSeats(@PathVariable String showId) {
        return showSeatService.getAvailableSeats(showId);
    }
}
