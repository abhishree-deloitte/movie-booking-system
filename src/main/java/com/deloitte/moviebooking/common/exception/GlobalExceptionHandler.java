package com.deloitte.moviebooking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * GlobalExceptionHandler handles all application exceptions
 * and converts them into meaningful HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handles application-level exceptions.
         */
        @ExceptionHandler(AppException.class)
        public ResponseEntity<?> handleAppException(AppException ex) {

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", ex.getMessage()
                        ));
        }

        /**
         * Handles validation errors from @Valid annotations.
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

                String errorMessage = ex.getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();

                return ResponseEntity
                        .badRequest()
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", errorMessage
                        ));
        }

        /**
         * Handles authorization failures (403 Forbidden).
         */
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "error", "Access is denied"
                ));
        }


        /**
         * Fallback handler for unexpected exceptions.
         */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleUnhandled(Exception ex) {

                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", "Something went wrong"
                        ));
        }
}
