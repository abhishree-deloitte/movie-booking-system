package com.deloitte.moviebooking.common.exception;

/**
 * AppException represents controlled application errors.
 *
 * It is used to send meaningful error messages
 * to the client without exposing internal details.
 */
public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }
}
