package com.deloitte.moviebooking.showseat.model;

import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.theatre.screen.seat.model.Seat;
import jakarta.persistence.*;

/**
 * Represents seat availability for a specific show.
 *
 * A seat is NOT globally available/unavailable.
 * Its availability is always evaluated in the context of a show.
 *
 * Lifecycle:
 * AVAILABLE -> BOOKED -> AVAILABLE (on cancellation)
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
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowSeatStatus status;

    protected ShowSeat() {}

    public ShowSeat(Show show, Seat seat) {
        this.show = show;
        this.seat = seat;
        this.status = ShowSeatStatus.AVAILABLE;
    }

    public ShowSeatStatus getStatus() {
        return status;
    }

    public void setStatus(ShowSeatStatus status) {
        this.status = status;
    }

    public Seat getSeat() {
        return seat;
    }
}
