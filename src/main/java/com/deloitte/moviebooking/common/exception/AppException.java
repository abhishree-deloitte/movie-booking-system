package com.deloitte.moviebooking.common.exception;

/**
 * Generic application-level exception.
 * Used to send controlled error messages to clients.
 */
public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }
}
