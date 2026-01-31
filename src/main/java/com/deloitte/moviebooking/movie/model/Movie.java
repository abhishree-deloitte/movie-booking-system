package com.deloitte.moviebooking.movie.model;

import jakarta.persistence.*;

/**
 * Movie entity represents a movie available for booking.
 * It also maintains aggregated rating information.
 */
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int durationMins;

    @Column(nullable = false)
    private double basePrice;

    /**
     * Average rating of the movie (1.0 - 5.0).
     */
    @Column(nullable = false)
    private double averageRating = 0.0;

    /**
     * Total number of ratings received.
     */
    @Column(nullable = false)
    private int ratingCount = 0;

    protected Movie() {
        // Required by JPA
    }

    public Movie(String title, String genre, int durationMins, double basePrice) {
        this.title = title;
        this.genre = genre;
        this.durationMins = durationMins;
        this.basePrice = basePrice;
    }

    /**
     * Adds a new rating and updates the average rating incrementally.
     *
     * @param rating new rating value
     */
    public void addRating(double rating) {
        double totalRating = this.averageRating * this.ratingCount;
        totalRating += rating;
        this.ratingCount++;
        this.averageRating = totalRating / this.ratingCount;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMins() {
        return durationMins;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}
