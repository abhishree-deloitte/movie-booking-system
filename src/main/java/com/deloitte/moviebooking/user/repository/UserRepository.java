package com.deloitte.moviebooking.user.repository;

import com.deloitte.moviebooking.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository handles all database operations
 * related to the User entity.
 *
 * It follows the Repository pattern and abstracts
 * database access from business logic.
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by username.
     *
     * Spring Data JPA automatically generates the query
     * based on the method name.
     *
     * @param username username to search for
     * @return Optional containing User if found, otherwise empty
     */
    Optional<User> findByUsername(String username);
}
