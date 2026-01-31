package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.movie.repository.MovieRepository;
import com.deloitte.moviebooking.movie.service.MovieService;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldCreateMovie() {
        // given movie details
        // when createMovie is called
        // then movie is saved
    }

    @Test
    void shouldAddRating() {
        // given existing movie
        // when rating is added
        // then avgRating is updated
    }
}