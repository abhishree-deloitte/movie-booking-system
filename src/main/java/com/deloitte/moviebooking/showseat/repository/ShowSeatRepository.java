package com.deloitte.moviebooking.showseat.repository;

import com.deloitte.moviebooking.showseat.model.ShowSeat;
import com.deloitte.moviebooking.showseat.model.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for ShowSeat entities.
 */
public interface ShowSeatRepository extends JpaRepository<ShowSeat, String> {

    List<ShowSeat> findByShow_ShowIdAndStatus(
            String showId,
            ShowSeatStatus status
    );
}
