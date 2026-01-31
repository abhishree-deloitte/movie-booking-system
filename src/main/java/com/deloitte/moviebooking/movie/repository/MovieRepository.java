package com.deloitte.moviebooking.movie.repository;

import com.deloitte.moviebooking.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Movie entity.
 */
public interface MovieRepository extends JpaRepository<Movie, String> {
}
