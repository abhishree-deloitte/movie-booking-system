package com.deloitte.moviebooking.movie.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import com.deloitte.moviebooking.theatre.model.Theatre;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Represents seat availability for a specific show.
 *
 * A seat is NOT globally available/unavailable.
 * Its availability is always evaluated in the context of a show.
 *
 * Lifecycle:
 * AVAILABLE -> BOOKED -> AVAILABLE (on cancellation)
 */
@Service
public class MovieDiscoveryService {

    private final ShowRepository showRepository;

    public MovieDiscoveryService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Theatre> getTheatresByMovie(String movieId) {
        List<Show> shows = showRepository.findByMovie_MovieId(movieId);

        if (shows.isEmpty()) {
            throw new AppException("No shows available for this movie");
        }

        return shows.stream()
                .map(show -> show.getScreen().getTheatre())
                .distinct()
                .toList();
    }
}
