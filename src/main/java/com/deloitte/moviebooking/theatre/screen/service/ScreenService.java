package com.deloitte.moviebooking.theatre.screen.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.theatre.model.Theatre;
import com.deloitte.moviebooking.theatre.repository.TheatreRepository;
import com.deloitte.moviebooking.theatre.screen.model.Screen;
import com.deloitte.moviebooking.theatre.screen.repository.ScreenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ScreenService contains business logic
 * related to screens inside theatres.
 */
@Service
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    public ScreenService(ScreenRepository screenRepository,
                         TheatreRepository theatreRepository) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    /**
     * Creates a new screen under a theatre.
     */
    public Screen createScreen(String theatreId, String screenName) {

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new AppException("Theatre not found"));

        Screen screen = new Screen(screenName, theatre);

        return screenRepository.save(screen);
    }

    /**
     * Fetches all screens for a theatre.
     */
    public List<Screen> getScreensByTheatre(String theatreId) {
        return screenRepository.findByTheatre_TheatreId(theatreId);
    }
}
