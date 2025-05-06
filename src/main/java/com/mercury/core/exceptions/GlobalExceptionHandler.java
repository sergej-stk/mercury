package com.mercury.core.exceptions;

import io.swagger.v3.oas.annotations.Hidden;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Centralized handler for exceptions across the application. Translates exceptions into consistent
 * API error responses.
 */
@Hidden
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Handles exceptions derived from UserDisplayException. Extracts details directly from the
     * exception to build the response DTO.
     *
     * @param ex The caught UserDisplayException (or subclass).
     * @param request The current web request.
     * @return A ResponseEntity containing the ErrorDetailsDTO and appropriate status.
     */
    @ExceptionHandler(UserDisplayException.class)
    public ResponseEntity<ErrorDetailsDTO> handleApplicationException(
            UserDisplayException ex, WebRequest request) {
        var errorCode = ex.getCode();
        var debugId = UUID.randomUUID();
        var path = ((ServletWebRequest) request).getRequest().getRequestURI();

        var result = new ErrorDetailsDTO()
                .setTimestamp(OffsetDateTime.now())
                .setStatus(errorCode.getStatus().value())
                .setErrorCode(errorCode.getErrorCode())
                .setMessage(errorCode.getMessage())
                .setPath(path)
                .setDebugId(debugId);

        log.error(
                "Handling UserDisplayException: Status='{}', Path='{}', ErrorCode='{}', DebugID='{}'",
                errorCode.getStatus(),
                path,
                errorCode.getErrorCode(),
                debugId,
                ex
        );

        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsDTO> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        var errorCode = ErrorCode.GENERAL_NOT_VALID_ARGUMENT;
        var debugId = UUID.randomUUID();
        var path = ((ServletWebRequest) request).getRequest().getRequestURI();

        var result = new ErrorDetailsDTO()
                .setTimestamp(OffsetDateTime.now())
                .setStatus(errorCode.getStatus().value())
                .setErrorCode(errorCode.getErrorCode())
                .setMessage(errorCode.getMessage())
                .setPath(path)
                .setDebugId(debugId);

        log.error(
                "Handling MethodArgumentNotValidException: Status='{}', Path='{}', ErrorCode='{}', DebugID='{}'",
                errorCode.getStatus(),
                path,
                errorCode.getErrorCode(),
                debugId,
                ex
        );

        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
