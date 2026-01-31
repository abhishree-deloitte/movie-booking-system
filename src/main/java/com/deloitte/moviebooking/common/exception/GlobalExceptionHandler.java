package com.deloitte.moviebooking.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

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

        @ExceptionHandler(AppException.class)
        public ResponseEntity<?> handleAppException(AppException ex) {

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "timestamp", LocalDateTime.now(),
                                "error", ex.getMessage()
                        ));
        }

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
