package com.mercury.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  GENERAL_NOT_VALID_ARGUMENT(
      HttpStatus.BAD_REQUEST,
      "E-GENERAL-0001",
      HttpStatus.BAD_REQUEST.getReasonPhrase(),
      HttpStatus.BAD_REQUEST.getReasonPhrase()),
  USER_EMAIL_ALREADY_EXISTS(
      HttpStatus.CONFLICT,
      "E-USER-0001",
      "User credentials already in use",
      "Email address already in use"),
  USER_USERNAME_ALREADY_EXISTS(
      HttpStatus.CONFLICT,
      "E-USER-0002",
      "User credentials already in use",
      "Username already in use"),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "E-USER-0003", "User not found", "User not found");

  private final HttpStatus status;
  private final String errorCode;
  private final String message;
  private final String debugMessage;
}
