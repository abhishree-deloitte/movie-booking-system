package com.deloitte.moviebooking.show.repository;

import com.deloitte.moviebooking.show.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Show entity.
 */
public interface ShowRepository extends JpaRepository<Show, String> {

    /**
     * Fetches all shows for a given movie.
     */
    List<Show> findByMovie_MovieId(String movieId);

    /**
     * Fetches all shows for a given theatre and movie.
     */
    List<Show> findByScreen_Theatre_TheatreIdAndMovie_MovieId(
            String theatreId,
            String movieId
    );
}
