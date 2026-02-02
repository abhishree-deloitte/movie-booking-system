package com.deloitte.moviebooking.booking.service;

import com.deloitte.moviebooking.booking.model.Booking;
import com.deloitte.moviebooking.booking.model.BookingStatus;
import com.deloitte.moviebooking.booking.repository.BookingRepository;
import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.notification.service.NotificationService;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.showseat.model.ShowSeat;
import com.deloitte.moviebooking.showseat.model.ShowSeatStatus;
import com.deloitte.moviebooking.showseat.repository.ShowSeatRepository;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import com.deloitte.moviebooking.theatre.screen.seat.model.SeatType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Handles booking creation and cancellation.
 *
 * Features:
 * - Seats are locked per show using ShowSeat
 * - Prevents double booking
 * - Seats are released on cancellation
 * - Notification logic remains unchanged
 */
@Service
public class BookingService {

    private static final double PREMIUM_SEAT_EXTRA = 50.0;

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final NotificationService notificationService;

    public BookingService(
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            NotificationService notificationService
    ) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.notificationService = notificationService;
    }

    /**
     * Creates a new booking with explicit seat selection.
     */
    @Transactional
    public Booking createBooking(
            String userId,
            String showId,
            List<String> seatIds
    ) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new AppException("Show not found"));

        if (seatIds == null || seatIds.isEmpty()) {
            throw new AppException("At least one seat must be selected");
        }

        // Fetch and validate show-specific seats
        List<ShowSeat> showSeats = seatIds.stream()
                .map(seatId ->
                        showSeatRepository
                                .findByShow_ShowIdAndSeat_SeatId(showId, seatId)
                                .orElseThrow(() ->
                                        new AppException("Seat not found for this show")
                                )
                )
                .toList();

        // Ensure all seats are AVAILABLE
        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getStatus() != ShowSeatStatus.AVAILABLE) {
                throw new AppException("Selected seat is no longer available");
            }
        }

        // Reserve seats
        showSeats.forEach(ShowSeat::reserve);
        showSeatRepository.saveAll(showSeats);

        // Pricing uses actual Seat objects (unchanged logic)
        List<Seat> seats = showSeats.stream()
                .map(ShowSeat::getSeat)
                .toList();

        double totalPrice = calculateTotalPrice(show, seats);

        Booking booking = new Booking(userId, show, seats, totalPrice);
        Booking savedBooking = bookingRepository.save(booking);

        // Confirm seats
        showSeats.forEach(ShowSeat::book);
        showSeatRepository.saveAll(showSeats);

        // Notification (UNCHANGED)
        notificationService.notifyUser(
                userId,
                "Booking confirmed for " + show.getMovie().getTitle()
        );

        return savedBooking;
    }

    /**
     * Cancels a booking and releases seats.
     */
    @Transactional
    public Booking cancelBooking(String bookingId, String userId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException("Booking not found"));

        if (!booking.getUserId().equals(userId)) {
            throw new AppException("Access denied");
        }

        if (booking.getStatus() != BookingStatus.UPCOMING) {
            throw new AppException("Booking cannot be cancelled");
        }

        // Release seats
        List<ShowSeat> showSeats = booking.getSeats().stream()
                .map(seat ->
                        showSeatRepository
                                .findByShow_ShowIdAndSeat_SeatId(
                                        booking.getShow().getShowId(),
                                        seat.getSeatId()
                                )
                                .orElseThrow(() ->
                                        new AppException("Seat mapping not found")
                                )
                )
                .toList();

        showSeats.forEach(ShowSeat::release);
        showSeatRepository.saveAll(showSeats);

        booking.setStatus(BookingStatus.CANCELLED);
        Booking savedBooking = bookingRepository.save(booking);

        // Notification (UNCHANGED)
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
     * Automatically update booking status based on show time.
     */
    private void updateBookingStatusIfNeeded(Booking booking) {

        if (booking.getStatus() == BookingStatus.UPCOMING &&
            booking.getShow().getStartTime().isBefore(LocalDateTime.now())) {

            booking.setStatus(BookingStatus.COMPLETED);
            bookingRepository.save(booking);
        }
    }

    /**
     * Pricing logic (UNCHANGED).
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
