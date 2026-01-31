package com.deloitte.moviebooking.theatre.model;

import jakarta.persistence.*;

/**
 * Theatre entity represents a physical movie theatre.
 *
 * A theatre can have multiple screens (audis),
 * which will be added in later phases.
 */
@Entity
@Table(name = "theatres")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String theatreId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    protected Theatre() {
        // Required by JPA
    }

    public Theatre(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
