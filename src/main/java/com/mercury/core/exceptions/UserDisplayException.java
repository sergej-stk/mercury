package com.mercury.core.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDisplayException extends RuntimeException {
  private final ErrorDetailsDTO details;

  public UserDisplayException(int status, String message, String errorCode) {
    details = new ErrorDetailsDTO()
            .setStatus(status)
            .setMessage(message)
            .setErrorCode(errorCode);
  }

}
