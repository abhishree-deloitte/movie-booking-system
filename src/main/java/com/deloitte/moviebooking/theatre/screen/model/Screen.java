package com.deloitte.moviebooking.theatre.screen.model;

import com.deloitte.moviebooking.theatre.model.Theatre;
import jakarta.persistence.*;

/**
 * Screen entity represents an audi inside a theatre.
 *
 * A screen belongs to exactly one theatre.
 */
@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;

    @Column(nullable = false)
    private String name; // e.g., Audi 1, Screen 2

    /**
     * Many screens can belong to one theatre.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    protected Screen() {
        // Required by JPA
    }

    public Screen(String name, Theatre theatre) {
        this.name = name;
        this.theatre = theatre;
    }

    public String getScreenId() {
        return screenId;
    }

    public String getName() {
        return name;
    }

    public Theatre getTheatre() {
        return theatre;
    }
}
