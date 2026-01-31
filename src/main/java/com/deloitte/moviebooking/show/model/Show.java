package com.deloitte.moviebooking.show.model;

import com.deloitte.moviebooking.movie.model.Movie;
import com.deloitte.moviebooking.theatre.screen.model.Screen;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Show entity represents a scheduled movie
 * running on a specific screen at a specific time.
 */
@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String showId;

    /**
     * Movie being played in this show.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    /**
     * Screen where the movie is played.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    /**
     * Show start time.
     */
    @Column(nullable = false)
    private LocalDateTime startTime;

    protected Show() {
        // Required by JPA
    }

    public Show(Movie movie, Screen screen, LocalDateTime startTime) {
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
