package com.deloitte.moviebooking.auth.controller;

import com.deloitte.moviebooking.auth.dto.AuthResponse;
import com.deloitte.moviebooking.auth.dto.LoginRequest;
import com.deloitte.moviebooking.auth.dto.RegisterRequest;
import com.deloitte.moviebooking.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController exposes authentication-related APIs.
 *
 * Endpoints:
 * - POST /auth/register
 * - POST /auth/login
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     *
     * @param request registration details
     * @return success message
     */
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        String token = authService.register(request);
        return new AuthResponse(token);
    }

    /**
     * Authenticates a user and returns JWT token.
     *
     * @param request login credentials
     * @return JWT token
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return new AuthResponse(token);
    }
}
