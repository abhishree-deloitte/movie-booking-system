package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.theatre.repository.TheatreRepository;
import com.deloitte.moviebooking.theatre.screen.repository.ScreenRepository;
import com.deloitte.moviebooking.theatre.screen.service.ScreenService;

@ExtendWith(MockitoExtension.class)
class ScreenServiceTest {

    @Mock
    private ScreenRepository screenRepository;

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private ScreenService screenService;

    @Test
    void shouldCreateScreenUnderTheatre() {
        // given theatre exists
        // when screen is created
        // then screen is saved
    }
}