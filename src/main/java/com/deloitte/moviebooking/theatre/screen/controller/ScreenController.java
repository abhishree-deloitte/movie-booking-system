package com.deloitte.moviebooking.theatre.screen.controller;

import com.deloitte.moviebooking.theatre.screen.model.Screen;
import com.deloitte.moviebooking.theatre.screen.service.ScreenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for screen-related APIs.
 *
 * Screens are always accessed in the context of a theatre.
 */
@RestController
@RequestMapping("/theatres/{theatreId}/screens")
public class ScreenController {

    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    /**
     * Creates a new screen under a theatre.
     * Allowed only for ADMIN.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Screen createScreen(
            @PathVariable String theatreId,
            @RequestParam String name
    ) {
        return screenService.createScreen(theatreId, name);
    }

    /**
     * Fetches all screens for a theatre.
     * Allowed for USER and ADMIN.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Screen> getScreens(@PathVariable String theatreId) {
        return screenService.getScreensByTheatre(theatreId);
    }
}
