package com.mercury.core.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Centralized handler for exceptions across the application. Translates exceptions into consistent
 * API error responses.
 */
@Hidden
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  private static final String GENERIC_USER_ERROR_FALLBACK_KEY = "error.general.user.fallback";
  private final MessageSource messageSource;

  @Autowired
  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Helper method to resolve localized messages.
   *
   * @param key The message key.
   * @param locale The current locale.
   * @param fallbackKey A key for a generic fallback message or direct text.
   * @return The localized message or a fallback message.
   */
  private String getLocalizedMessage(String key, Locale locale, String fallbackKey) {
    try {
      return messageSource.getMessage(key, null, locale);
    } catch (NoSuchMessageException e) {
      log.warn(
          "Message key '{}' for locale '{}' not found. Attempting fallback key '{}'.",
          key,
          locale,
          fallbackKey,
          e);
      try {
        return messageSource.getMessage(fallbackKey, null, locale);
      } catch (NoSuchMessageException e2) {
        log.warn(
            "Fallback message key '{}' for locale '{}' also not found. Returning original key '{}'"
                + " as text.",
            fallbackKey,
            locale,
            key,
            e2);
        if (GENERIC_USER_ERROR_FALLBACK_KEY.equals(fallbackKey)) {
          return "An internal error occurred. Please contact support.";
        }
        return key;
      }
    }
  }

  /** Builds the ErrorDetailsDTO. */
  private ErrorDetailsDTO buildErrorDetailsDTO(
      HttpStatus status, String errorCodeStr, String message, String path, UUID debugId) {
    return new ErrorDetailsDTO()
        .setTimestamp(OffsetDateTime.now())
        .setStatus(status.value())
        .setErrorCode(errorCodeStr)
        .setMessage(message)
        .setPath(path)
        .setDebugId(debugId);
  }

  /** Centralized logging for error details. */
  private void logErrorDetails(
      String handlerMethodName,
      ErrorDetailsDTO details,
      ErrorCode errorCode,
      String resolvedDebugMessage,
      Exception originalException) {
    log.error(
        "{} handled: Status='{}', Path='{}', ErrorCode='{}', UserMessageKey='{}', UserMessage='{}',"
            + " DebugMessageKey='{}', ResolvedDebugMessage='{}', DebugID='{}'",
        handlerMethodName,
        details.getStatus(),
        details.getPath(),
        details.getErrorCode(),
        errorCode.getErrorCode(),
        details.getMessage(),
        errorCode.getDebugMessage(),
        resolvedDebugMessage,
        details.getDebugId(),
        originalException);
  }

  @ExceptionHandler(UserDisplayException.class)
  public ResponseEntity<ErrorDetailsDTO> handleUserDisplayException(
      UserDisplayException ex, WebRequest request) {
    ErrorCode errorCode = ex.getCode();
    UUID debugId = UUID.randomUUID();
    String path = ((ServletWebRequest) request).getRequest().getRequestURI();
    Locale locale = request.getLocale();

    String localizedUserMessage =
        getLocalizedMessage(errorCode.getMessage(), locale, GENERIC_USER_ERROR_FALLBACK_KEY);
    String localizedDebugMessage =
        getLocalizedMessage(errorCode.getDebugMessage(), locale, errorCode.getErrorCode());

    ErrorDetailsDTO errorDetails =
        buildErrorDetailsDTO(
            errorCode.getStatus(), errorCode.getErrorCode(), localizedUserMessage, path, debugId);

    logErrorDetails(
        "handleUserDisplayException", errorDetails, errorCode, localizedDebugMessage, ex);

    return ResponseEntity.status(errorDetails.getStatus()).body(errorDetails);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetailsDTO> handleValidationException(
      MethodArgumentNotValidException ex, WebRequest request) {
    var errorCode = ErrorCode.GENERAL_NOT_VALID_ARGUMENT;
    var debugId = UUID.randomUUID();
    var path = ((ServletWebRequest) request).getRequest().getRequestURI();

    var result =
        new ErrorDetailsDTO()
            .setTimestamp(OffsetDateTime.now())
            .setStatus(errorCode.getStatus().value())
            .setErrorCode(errorCode.getErrorCode())
            .setMessage(errorCode.getMessage())
            .setPath(path)
            .setDebugId(debugId);

    log.error(
        "Handling MethodArgumentNotValidException: Status='{}', Path='{}', ErrorCode='{}',"
            + " DebugID='{}'",
        errorCode.getStatus(),
        path,
        errorCode.getErrorCode(),
        debugId,
        ex);

    return ResponseEntity.status(result.getStatus()).body(result);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetailsDTO> handleGenericException(Exception ex, WebRequest request) {
    ErrorCode errorCode = ErrorCode.GENERAL_INTERNAL_SERVER_ERROR;
    UUID debugId = UUID.randomUUID();
    String path = ((ServletWebRequest) request).getRequest().getRequestURI();
    Locale locale = request.getLocale();

    String localizedUserMessage =
        getLocalizedMessage(errorCode.getMessage(), locale, GENERIC_USER_ERROR_FALLBACK_KEY);
    String localizedDebugMessage =
        getLocalizedMessage(
            errorCode.getDebugMessage(),
            locale,
            (ex.getMessage() != null ? ex.getMessage() : "No specific debug information."));

    ErrorDetailsDTO errorDetails =
        buildErrorDetailsDTO(
            errorCode.getStatus(), errorCode.getErrorCode(), localizedUserMessage, path, debugId);

    logErrorDetails("handleGenericException", errorDetails, errorCode, localizedDebugMessage, ex);

    return ResponseEntity.status(errorDetails.getStatus()).body(errorDetails);
  }
}
