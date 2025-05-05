package com.mercury.modules.users.shared.exceptions;

import com.mercury.core.exceptions.UserDisplayException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends UserDisplayException {
  public EmailAlreadyExistsException(int status, String message, String errorCode) {
    super(status, message, errorCode);
  }

  public EmailAlreadyExistsException() {
    super(
        HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), "USER_ALREADY_EXISTS");
    }
}
