package com.deloitte.moviebooking.show.controller;

import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.service.ShowService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for show-related APIs.
 *
 * Shows are accessed in the context of a movie.
 */
@RestController
@RequestMapping("/movies/{movieId}/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    /**
     * Schedules a movie show.
     * Allowed only for ADMIN.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Show createShow(
            @PathVariable String movieId,
            @RequestParam String screenId,
            @RequestParam String startTime
    ) {
        return showService.createShow(
                movieId,
                screenId,
                LocalDateTime.parse(startTime)
        );
    }

    /**
     * Fetches all shows for a movie.
     * Allowed for USER and ADMIN.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Show> getShows(@PathVariable String movieId) {
        return showService.getShowsByMovie(movieId);
    }
}
