package com.deloitte.moviebooking.show.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.movie.model.Movie;
import com.deloitte.moviebooking.movie.repository.MovieRepository;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.theatre.screen.model.Screen;
import com.deloitte.moviebooking.theatre.screen.repository.ScreenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ShowService contains business logic
 * related to scheduling movies.
 */
@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public ShowService(ShowRepository showRepository,
                       MovieRepository movieRepository,
                       ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    /**
     * Schedules a movie on a screen at a specific time.
     */
    public Show createShow(String movieId,
                           String screenId,
                           LocalDateTime startTime) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException("Movie not found"));

        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new AppException("Screen not found"));

        Show show = new Show(movie, screen, startTime);

        return showRepository.save(show);
    }

    /**
     * Fetches all shows for a movie.
     */
    public List<Show> getShowsByMovie(String movieId) {
        return showRepository.findByMovie_MovieId(movieId);
    }
}
