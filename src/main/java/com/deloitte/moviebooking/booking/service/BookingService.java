package com.deloitte.moviebooking.booking.service;

import com.deloitte.moviebooking.booking.model.Booking;
import com.deloitte.moviebooking.booking.model.BookingStatus;
import com.deloitte.moviebooking.booking.repository.BookingRepository;
import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.notification.service.NotificationService;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import com.deloitte.moviebooking.theatre.screen.seat.model.SeatType;
import com.deloitte.moviebooking.theatre.screen.seat.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * BookingService contains business logic related to bookings.
 */
@Service
public class BookingService {

    private static final double PREMIUM_SEAT_EXTRA = 50.0;

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final NotificationService notificationService;

    public BookingService(
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            SeatRepository seatRepository,
            NotificationService notificationService
    ) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.notificationService = notificationService;
    }

    /**
     * Creates a new booking.
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

        double totalPrice = calculateTotalPrice(show, seats);

        Booking booking = new Booking(userId, show, seats, totalPrice);

        Booking savedBooking = bookingRepository.save(booking);

        // Notification
        notificationService.notifyUser(
                userId,
                "Booking confirmed for " + show.getMovie().getTitle()
        );

        return savedBooking;
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

        Booking savedBooking = bookingRepository.save(booking);

        // Notification
        notificationService.notifyUser(
                userId,
                "Booking cancelled for " +
                        booking.getShow().getMovie().getTitle()
        );

        return savedBooking;
    }

    /**
     * Fetch bookings for a user.
     */
    public List<Booking> getBookingsForUser(String userId) {

        List<Booking> bookings = bookingRepository.findByUserId(userId);

        bookings.forEach(this::updateBookingStatusIfNeeded);

        return bookings;
    }

    /**
     * Fetch booking by ID.
     */
    public Booking getBookingById(String bookingId, String userId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException("Booking not found"));

        if (!booking.getUserId().equals(userId)) {
            throw new AppException("Access denied");
        }

        updateBookingStatusIfNeeded(booking);

        return booking;
    }

    /**
     * Update booking status automatically.
     */
    private void updateBookingStatusIfNeeded(Booking booking) {

        if (booking.getStatus() == BookingStatus.UPCOMING &&
            booking.getShow().getStartTime().isBefore(LocalDateTime.now())) {

            booking.setStatus(BookingStatus.COMPLETED);
            bookingRepository.save(booking);
        }
    }

    /**
     * Pricing logic.
     */
    private double calculateTotalPrice(Show show, List<Seat> seats) {

        double basePrice = show.getMovie().getBasePrice();
        double total = 0;

        for (Seat seat : seats) {
            total += basePrice;

            if (seat.getType() == SeatType.PREMIUM) {
                total += PREMIUM_SEAT_EXTRA;
            }
        }

        return total;
    }
}
