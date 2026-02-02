package com.deloitte.moviebooking.movie.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.deloitte.moviebooking.movie.service.MovieDiscoveryService;
import com.deloitte.moviebooking.theatre.model.Theatre;

import java.util.List;

/**
 * Provides seat availability for a show.
 *
 * Only seats with status AVAILABLE are returned.
 * BOOKED seats are hidden from users.
 */
@RestController
@RequestMapping("/movies")
public class MovieDiscoveryController {

    private final MovieDiscoveryService discoveryService;

    public MovieDiscoveryController(MovieDiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{movieId}/theatres")
    public List<Theatre> getTheatres(@PathVariable String movieId) {
        return discoveryService.getTheatresByMovie(movieId);
    }
}
