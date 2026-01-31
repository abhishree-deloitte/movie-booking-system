package com.deloitte.moviebooking.controller;

import com.deloitte.moviebooking.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    public MovieController() {
        movies.add(new Movie("M1", "Inception", "Sci-Fi", 148, 250.0));
        movies.add(new Movie("M2", "Interstellar", "Sci-Fi", 169, 300.0));
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movies;
    }

    @PostMapping("/{movieId}/rating")
    public String submitRating(
            @PathVariable String movieId,
            @RequestParam double rating
    ) {
        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                movie.addRating(rating);
                return "Rating submitted successfully";
            }
        }
        return "Movie not found";
    }
}
