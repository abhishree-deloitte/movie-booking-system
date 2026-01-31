package com.deloitte.moviebooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public String getAllMovies() {
        return "Movie service is up and running";
    }
}
