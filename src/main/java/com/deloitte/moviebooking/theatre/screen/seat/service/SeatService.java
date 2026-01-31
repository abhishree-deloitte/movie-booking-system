package com.deloitte.moviebooking.theatre.screen.seat.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.theatre.screen.model.Screen;
import com.deloitte.moviebooking.theatre.screen.repository.ScreenRepository;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import com.deloitte.moviebooking.theatre.screen.seat.model.SeatType;
import com.deloitte.moviebooking.theatre.screen.seat.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SeatService contains business logic
 * related to seat management inside screens.
 */
@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final ScreenRepository screenRepository;

    public SeatService(SeatRepository seatRepository,
                       ScreenRepository screenRepository) {
        this.seatRepository = seatRepository;
        this.screenRepository = screenRepository;
    }

    /**
     * Creates a new seat under a screen.
     */
    public Seat createSeat(String screenId,
                           String row,
                           int number,
                           SeatType type) {

        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new AppException("Screen not found"));

        Seat seat = new Seat(row, number, type, screen);

        return seatRepository.save(seat);
    }

    /**
     * Fetches all seats for a screen.
     */
    public List<Seat> getSeatsByScreen(String screenId) {
        return seatRepository.findByScreen_ScreenId(screenId);
    }
}
