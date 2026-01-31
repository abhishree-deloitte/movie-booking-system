package com.deloitte.moviebooking.auth.service;

import com.deloitte.moviebooking.auth.dto.LoginRequest;
import com.deloitte.moviebooking.auth.dto.RegisterRequest;
import com.deloitte.moviebooking.auth.security.JwtUtil;
import com.deloitte.moviebooking.user.model.User;
import com.deloitte.moviebooking.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthService contains business logic related to
 * authentication and user management.
 *
 * It handles:
 * - User registration
 * - User login
 * - Password encryption
 * - JWT generation
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Registers a new user in the system.
     *
     * @param request registration details
     */
    public String register(RegisterRequest request) {
        try {
            User user = new User(
                    request.username,
                    request.email,
                    passwordEncoder.encode(request.password),
                    request.role
            );

            User savedUser = userRepository.save(user);

            // Auto-login after registration
            return jwtUtil.generateToken(savedUser.getUserId(), savedUser.getRole());

        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Username already exists");
        }
    }


    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param request login credentials
     * @return JWT token string
     */
    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUserId(), user.getRole());
    }
}
