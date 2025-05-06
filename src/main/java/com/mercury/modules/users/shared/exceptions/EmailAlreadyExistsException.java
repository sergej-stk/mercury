package com.mercury.modules.users.shared.exceptions;

import com.mercury.core.exceptions.ErrorCode;
import com.mercury.core.exceptions.UserDisplayException;

public class EmailAlreadyExistsException extends UserDisplayException {
    public EmailAlreadyExistsException() {
        super(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
    }
}
