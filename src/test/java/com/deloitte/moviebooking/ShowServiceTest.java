package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.movie.repository.MovieRepository;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.show.service.ShowService;
import com.deloitte.moviebooking.theatre.screen.repository.ScreenRepository;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ScreenRepository screenRepository;

    @InjectMocks
    private ShowService showService;

    @Test
    void shouldScheduleShow() {
        // given movie and screen exist
        // when show is scheduled
        // then show is saved
    }
}