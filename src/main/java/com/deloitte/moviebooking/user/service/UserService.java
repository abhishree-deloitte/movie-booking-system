package com.deloitte.moviebooking.user.service;

import com.deloitte.moviebooking.common.exception.AppException;
import com.deloitte.moviebooking.user.model.User;
import com.deloitte.moviebooking.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * UserService contains business logic
 * related to user profile management.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Fetch user profile.
     */
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found"));
    }

    /**
     * Update username.
     */
    public User updateUsername(String userId, String newUsername) {

        if (newUsername == null || newUsername.isBlank()) {
            throw new AppException("Username cannot be empty");
        }

        User user = getUserById(userId);
        user.setUsername(newUsername);

        return userRepository.save(user);
    }

    /**
     * Update email.
     */
    public User updateEmail(String userId, String newEmail) {

        if (newEmail == null || newEmail.isBlank()) {
            throw new AppException("Email cannot be empty");
        }

        User user = getUserById(userId);
        user.setEmail(newEmail);

        return userRepository.save(user);
    }
}
