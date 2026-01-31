package com.deloitte.moviebooking.model;

public class Movie {

    private String movieId;
    private String title;
    private String genre;
    private int durationMins;
    private double ticketPrice;

    private double averageRating;
    private int ratingCount;

    public Movie(String movieId, String title, String genre, int durationMins, double ticketPrice) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMins = durationMins;
        this.ticketPrice = ticketPrice;
        this.averageRating = 0.0;
        this.ratingCount = 0;
    }

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

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}
