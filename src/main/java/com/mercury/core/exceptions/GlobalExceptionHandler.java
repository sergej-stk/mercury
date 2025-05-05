package com.mercury.core.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

/**
 * Centralized handler for exceptions across the application.
 * Translates exceptions into consistent API error responses.
 */
@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles exceptions derived from UserDisplayException.
     * Extracts details directly from the exception to build the response DTO.
     *
     * @param ex      The caught UserDisplayException (or subclass).
     * @param request The current web request.
     * @return A ResponseEntity containing the ErrorDetailsDTO and appropriate status.
     */
    @ExceptionHandler(UserDisplayException.class)
    public ResponseEntity<ErrorDetailsDTO> handleApplicationException(UserDisplayException ex, WebRequest request) {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        ex.getDetails().setPath(path);
        ex.getDetails().setTimestamp(OffsetDateTime.now());

        return ResponseEntity
                .status(ex.getDetails().getStatus())
                .body(ex.getDetails());
    }
}
