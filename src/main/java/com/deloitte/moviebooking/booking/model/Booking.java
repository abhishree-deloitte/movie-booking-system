package com.deloitte.moviebooking.booking.model;

import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Booking entity represents a user's booking for a show.
 */
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bookingId;

    /**
     * User who made the booking.
     * Stored as userId from JWT.
     */
    @Column(nullable = false)
    private String userId;

    /**
     * Show for which booking is made.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id")
    private Show show;

    /**
     * Seats booked in this booking.
     */
    @ManyToMany
    @JoinTable(
        name = "booking_seats",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;

    /**
     * Booking status.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    /**
     * Timestamp when booking was created.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Booking() {
        // JPA
    }

    public Booking(String userId, Show show, List<Seat> seats) {
        this.userId = userId;
        this.show = show;
        this.seats = seats;
        this.status = BookingStatus.UPCOMING;
        this.createdAt = LocalDateTime.now();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
