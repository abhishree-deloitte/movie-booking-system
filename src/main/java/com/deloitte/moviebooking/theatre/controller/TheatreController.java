package com.deloitte.moviebooking.theatre.controller;

import com.deloitte.moviebooking.theatre.model.Theatre;
import com.deloitte.moviebooking.theatre.service.TheatreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for theatre-related APIs.
 */
@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    /**
     * Creates a new theatre.
     * Allowed only for ADMIN.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Theatre createTheatre(@RequestBody Theatre theatre) {
        return theatreService.createTheatre(theatre);
    }

    /**
     * Fetches all theatres.
     * Allowed for USER and ADMIN.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }
}
