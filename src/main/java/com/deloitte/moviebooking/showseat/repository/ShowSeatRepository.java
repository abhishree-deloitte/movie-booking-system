package com.deloitte.moviebooking.showseat.repository;

import com.deloitte.moviebooking.showseat.model.ShowSeat;
import com.deloitte.moviebooking.showseat.model.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * Repository for ShowSeat entities.
 */
public interface ShowSeatRepository extends JpaRepository<ShowSeat, String> {

    Optional<ShowSeat> findByShow_ShowIdAndSeat_SeatId(
            String showId,
            String seatId
    );

    List<ShowSeat> findByShow_ShowIdAndStatus(
            String showId,
            ShowSeatStatus status
    );
}
