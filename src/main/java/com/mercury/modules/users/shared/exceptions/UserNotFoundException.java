package com.mercury.modules.users.shared.exceptions;

import com.mercury.core.exceptions.ErrorCode;
import com.mercury.core.exceptions.UserDisplayException;

public class UserNotFoundException extends UserDisplayException {
  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }
}
