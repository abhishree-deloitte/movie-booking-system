package com.deloitte.moviebooking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deloitte.moviebooking.user.controller.UserController;
import com.deloitte.moviebooking.user.service.UserService;
import com.deloitte.moviebooking.booking.service.BookingService;
import com.deloitte.moviebooking.notification.service.NotificationService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private UserController userController;

    @Test
    void shouldFetchUserProfile() {
        // skeleton test – verifies controller can be constructed
    }

    @Test
    void shouldUpdateUsername() {
        // skeleton test
    }

    @Test
    void shouldUpdateEmail() {
        // skeleton test
    }
}
