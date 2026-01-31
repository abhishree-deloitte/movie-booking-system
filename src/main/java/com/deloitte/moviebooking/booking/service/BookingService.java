package com.deloitte.moviebooking.booking.service;

import com.deloitte.moviebooking.booking.model.Booking;
import com.deloitte.moviebooking.booking.model.BookingStatus;
import com.deloitte.moviebooking.booking.repository.BookingRepository;
import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import com.deloitte.moviebooking.theatre.screen.seat.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BookingService contains business logic
 * related to bookings.
 */
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    public BookingService(BookingRepository bookingRepository,
                          ShowRepository showRepository,
                          SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    /**
     * Creates a new booking for a user.
     */
    public Booking createBooking(String userId,
                                 String showId,
                                 List<String> seatIds) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new AppException("Show not found"));

        List<Seat> seats = seatRepository.findAllById(seatIds);

        if (seats.isEmpty()) {
            throw new AppException("No seats selected");
        }

        Booking booking = new Booking(userId, show, seats);

        return bookingRepository.save(booking);
    }

    /**
     * Fetch bookings for a user.
     */
    public List<Booking> getBookingsForUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    /**
     * Fetch a single booking.
     */
    public Booking getBookingById(String bookingId, String userId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException("Booking not found"));

        if (!booking.getUserId().equals(userId)) {
            throw new AppException("Access denied");
        }

        return booking;
    }

    /**
     * Cancels a booking.
     */
    public Booking cancelBooking(String bookingId, String userId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException("Booking not found"));

        if (!booking.getUserId().equals(userId)) {
            throw new AppException("Access denied");
        }

        if (booking.getStatus() != BookingStatus.UPCOMING) {
            throw new AppException("Booking cannot be cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        return bookingRepository.save(booking);
    }
}
