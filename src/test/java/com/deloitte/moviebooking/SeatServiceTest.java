package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.theatre.screen.repository.ScreenRepository;
import com.deloitte.moviebooking.theatre.screen.seat.repository.SeatRepository;
import com.deloitte.moviebooking.theatre.screen.seat.service.SeatService;

@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ScreenRepository screenRepository;

    @InjectMocks
    private SeatService seatService;

    @Test
    void shouldCreateSeat() {
        // given screen exists
        // when seat is created
        // then seat is saved
    }
}