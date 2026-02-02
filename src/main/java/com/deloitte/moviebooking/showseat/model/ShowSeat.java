package com.deloitte.moviebooking.showseat.model;

import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import jakarta.persistence.*;

/**
 * Represents seat availability in the context of a show.
 *
 * Seats are NOT global; they are evaluated per show.
 *
 * Lifecycle:
 * AVAILABLE -> RESERVED -> BOOKED
 * BOOKED -> AVAILABLE (on cancellation)
 */
@Entity
@Table(
    name = "show_seats",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"show_id", "seat_id"})
    }
)
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showSeatId;

    @ManyToOne(optional = false)
    private Show show;

    @ManyToOne(optional = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowSeatStatus status;

    protected ShowSeat() {}

    public void reserve() {
        this.status = ShowSeatStatus.RESERVED;
    }

    public void book() {
        this.status = ShowSeatStatus.BOOKED;
    }

    public void release() {
        this.status = ShowSeatStatus.AVAILABLE;
    }

    public ShowSeatStatus getStatus() {
        return status;
    }

    public Seat getSeat() {
        return seat;
    }
}
