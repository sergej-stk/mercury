package com.mercury.modules.users.shared.exceptions;

import com.mercury.core.exceptions.UserDisplayException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends UserDisplayException {
  public UsernameAlreadyExistsException(int status, String message, String errorCode) {
    super(status, message, errorCode);
  }

  public UsernameAlreadyExistsException() {
    super(
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            "USER_ALREADY_EXISTS"
    );
  }
}
