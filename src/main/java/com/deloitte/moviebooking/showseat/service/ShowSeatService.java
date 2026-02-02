package com.deloitte.moviebooking.showseat.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.showseat.model.ShowSeat;
import com.deloitte.moviebooking.showseat.model.ShowSeatStatus;
import com.deloitte.moviebooking.showseat.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides seat availability for a show.
 *
 * Only seats with status AVAILABLE are returned.
 * BOOKED seats are hidden from users.
 */
@Service
public class ShowSeatService {

    private final ShowSeatRepository showSeatRepository;

    public ShowSeatService(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public List<ShowSeat> getAvailableSeats(String showId) {
        List<ShowSeat> seats =
                showSeatRepository.findByShow_ShowIdAndStatus(
                        showId,
                        ShowSeatStatus.AVAILABLE
                );

        if (seats.isEmpty()) {
            throw new AppException("No seats available for this show");
        }

        return seats;
    }
}
