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
      "error.general.notValidArgument.message",
      "error.general.notValidArgument.debug"),
  GENERAL_INTERNAL_SERVER_ERROR(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "E-GENERAL-0002",
      "error.general.internalservererror.message",
      "error.general.internalservererror.debug"),
  USER_EMAIL_ALREADY_EXISTS(
      HttpStatus.CONFLICT,
      "E-USER-0001",
      "error.user.emailAlreadyExists.message",
      "error.user.emailAlreadyExists.debug"),
  USER_USERNAME_ALREADY_EXISTS(
      HttpStatus.CONFLICT,
      "E-USER-0002",
      "error.user.usernameAlreadyExists.message",
      "error.user.usernameAlreadyExists.debug"),
  USER_NOT_FOUND(
      HttpStatus.NOT_FOUND,
      "E-USER-0003",
      "error.user.notFound.message",
      "error.user.notFound.debug");

  private final HttpStatus status;
  private final String errorCode;
  private final String message;
  private final String debugMessage;
}
