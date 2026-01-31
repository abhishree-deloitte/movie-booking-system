package com.deloitte.moviebooking.theatre.screen.seat.model;

import com.deloitte.moviebooking.theatre.screen.model.Screen;
import jakarta.persistence.*;

/**
 * Seat entity represents a seat inside a screen.
 *
 * Seats belong to a specific screen and
 * have a type that affects pricing.
 */
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;

    @Column(nullable = false)
    private String seatRow; // A, B, C

    @Column(nullable = false)
    private int seatNumber; // 1, 2, 3


    /**
     * Seat type used for pricing.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType type;

    /**
     * Availability at screen level.
     * (Later moved to show-level availability)
     */
    @Column(nullable = false)
    private boolean available = true;

    /**
     * Many seats belong to one screen.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    protected Seat() {
        // Required by JPA
    }

    public Seat(String seatRow, int seatNumber, SeatType type, Screen screen) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.type = type;
        this.screen = screen;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public Screen getScreen() {
        return screen;
    }
}
