package com.deloitte.moviebooking.movie.controller;

import com.deloitte.moviebooking.movie.model.Movie;
import com.deloitte.moviebooking.movie.service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * REST controller for movie-related APIs.
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Creates a new movie (ADMIN use).
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    /**
     * Fetches all movies available for booking.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * Submits a rating for a movie.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{movieId}/rating")
    public Movie rateMovie(
            @PathVariable String movieId,
            @RequestParam double rating
    ) {
        return movieService.rateMovie(movieId, rating);
    }
}
