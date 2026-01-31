package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.booking.repository.BookingRepository;
import com.deloitte.moviebooking.booking.service.BookingService;
import com.deloitte.moviebooking.notification.service.NotificationService;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.theatre.screen.seat.repository.SeatRepository;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void shouldCreateBookingAndCalculatePrice() {
        // given show and seats
        // when booking is created
        // then price is calculated and booking saved
    }

    @Test
    void shouldCancelBooking() {
        // given upcoming booking
        // when cancel is called
        // then status becomes CANCELLED
    }
}