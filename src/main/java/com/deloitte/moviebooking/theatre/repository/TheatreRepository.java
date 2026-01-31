package com.deloitte.moviebooking.theatre.repository;

import com.deloitte.moviebooking.theatre.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Theatre entity.
 */
public interface TheatreRepository extends JpaRepository<Theatre, String> {
}
