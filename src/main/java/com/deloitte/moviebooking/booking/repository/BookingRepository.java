package com.deloitte.moviebooking.booking.repository;

import com.deloitte.moviebooking.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, String> {

    /**
     * Fetch all bookings for a user.
     */
    List<Booking> findByUserId(String userId);
}
