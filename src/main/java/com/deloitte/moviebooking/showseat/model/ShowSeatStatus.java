package com.deloitte.moviebooking.showseat.model;

/**
 * Represents the availability of a seat for a specific show.
 *
 * AVAILABLE -> RESERVED -> BOOKED
 * RESERVED seats prevent concurrent bookings.
 */
public enum ShowSeatStatus {
    AVAILABLE,
    RESERVED,
    BOOKED
}
