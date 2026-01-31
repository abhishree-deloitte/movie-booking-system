package com.deloitte.moviebooking.theatre.screen.seat.repository;

import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Seat entity.
 */
public interface SeatRepository extends JpaRepository<Seat, String> {

    /**
     * Fetches all seats for a given screen.
     */
    List<Seat> findByScreen_ScreenId(String screenId);
}
