package com.deloitte.moviebooking.movie.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.movie.model.Movie;
import com.deloitte.moviebooking.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MovieService contains business logic related to movies.
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Creates a new movie.
     */
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Fetches all available movies.
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie rateMovie(String movieId, double rating) {

        if (rating < 1 || rating > 5) {
            throw new AppException("Rating must be between 1 and 5");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException("Movie not found"));

        movie.addRating(rating);

        return movieRepository.save(movie);
    }

}
