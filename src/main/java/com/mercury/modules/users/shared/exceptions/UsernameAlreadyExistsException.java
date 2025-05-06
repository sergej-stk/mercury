package com.mercury.modules.users.shared.exceptions;

import com.mercury.core.exceptions.ErrorCode;
import com.mercury.core.exceptions.UserDisplayException;

public class UsernameAlreadyExistsException extends UserDisplayException {
    public UsernameAlreadyExistsException() {
        super(ErrorCode.USER_USERNAME_ALREADY_EXISTS);
    }
}
