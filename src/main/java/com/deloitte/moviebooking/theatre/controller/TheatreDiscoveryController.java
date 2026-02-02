package com.deloitte.moviebooking.theatre.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.deloitte.moviebooking.show.model.Show;
import com.deloitte.moviebooking.show.service.ShowDiscoveryService;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreDiscoveryController {

    private final ShowDiscoveryService showDiscoveryService;

    public TheatreDiscoveryController(
            ShowDiscoveryService showDiscoveryService
    ) {
        this.showDiscoveryService = showDiscoveryService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{theatreId}/movies/{movieId}/shows")
    public List<Show> getShows(
            @PathVariable String theatreId,
            @PathVariable String movieId
    ) {
        return showDiscoveryService.getShows(theatreId, movieId);
    }
}
