package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.auth.controller.AuthController;
import com.deloitte.moviebooking.auth.service.AuthService;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void shouldRegisterUser() {
        // skeleton test – controller can be instantiated with mocked service
    }

    @Test
    void shouldLoginUser() {
        // skeleton test – controller wiring verified
    }
}
