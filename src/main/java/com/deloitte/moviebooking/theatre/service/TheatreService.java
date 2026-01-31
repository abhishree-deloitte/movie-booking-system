package com.deloitte.moviebooking.theatre.service;

import com.deloitte.moviebooking.theatre.model.Theatre;
import com.deloitte.moviebooking.theatre.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TheatreService contains business logic
 * related to theatre management.
 */
@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    /**
     * Creates a new theatre.
     */
    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    /**
     * Fetches all theatres.
     */
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }
}
