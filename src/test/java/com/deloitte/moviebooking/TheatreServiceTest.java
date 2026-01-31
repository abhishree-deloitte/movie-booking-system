package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.theatre.repository.TheatreRepository;
import com.deloitte.moviebooking.theatre.service.TheatreService;

@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreService theatreService;

    @Test
    void shouldCreateTheatre() {
        // given theatre details
        // when createTheatre is called
        // then theatre is saved
    }
}
