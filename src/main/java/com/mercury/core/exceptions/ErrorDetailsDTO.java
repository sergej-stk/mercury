package com.mercury.core.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(
    name = "ErrorDetails",
    title = "Error Details",
    description =
        "Represents a standardized error response containing essential details like"
            + " timestamp, HTTP status code, a unique error code, an error message, and the"
            + " API endpoint that generated the response.")
public final class ErrorDetailsDTO {

  @Schema(
      name = "timestamp",
      title = "Timestamp",
      description =
          "The date and time (with time zone offset) when the error response was" + " generated.",
      example = "2025-05-06T00:35:09.123+02:00")
  private OffsetDateTime timestamp;

  @Schema(
      name = "status",
      title = "HTTP Status Code",
      description = "The HTTP status code indicating the outcome of the API request.",
      example = "404")
  private int status;

  @Schema(
      name = "errorCode",
      title = "Internal Error Code",
      description =
          "A unique, application-specific, machine-readable code identifying the type of"
              + " error. Useful for programmatic error handling by clients.",
      example = "E-USER-0002")
  private String errorCode;

  @Schema(
      name = "message",
      title = "Error Message",
      description = "A human-readable description of the error that occurred.",
      example = "Item with ID '123' could not be found.")
  private String message;

  @Schema(
      name = "path",
      title = "API Path",
      description = "The API endpoint (path) that produced this error response.",
      example = "/api/v1/items/123")
  private String path;

  @Schema(
      name = "debugId",
      title = "Debug ID",
      description =
          "A unique identifier (UUID) assigned to this specific error instance, useful"
              + " for debugging and tracing purposes (e.g., correlating with logs).",
      example = "f81d4fae-7dec-11d0-a765-00a0c91e6bf6")
  private UUID debugId;
}
