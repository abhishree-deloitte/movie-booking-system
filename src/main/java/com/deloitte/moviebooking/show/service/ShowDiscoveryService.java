package com.deloitte.moviebooking.show.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handles discovery of shows for a given movie in a theatre.
 *
 * Flow:
 * Theatre + Movie -> Shows
 *
 * Throws AppException if no shows exist.
 */
@Service
public class ShowDiscoveryService {

    private final ShowRepository showRepository;

    public ShowDiscoveryService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getShows(String theatreId, String movieId) {
        List<Show> shows =
                showRepository.findByScreen_Theatre_TheatreIdAndMovie_MovieId(
                        theatreId,
                        movieId
                );

        if (shows.isEmpty()) {
            throw new AppException(
                "No shows available in this theatre for selected movie"
            );
        }

        return shows;
    }
}
