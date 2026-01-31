package com.deloitte.moviebooking.theatre.screen.repository;

import com.deloitte.moviebooking.theatre.screen.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Screen entity.
 */
public interface ScreenRepository extends JpaRepository<Screen, String> {

    /**
     * Fetches all screens for a given theatre.
     */
    List<Screen> findByTheatre_TheatreId(String theatreId);
}
