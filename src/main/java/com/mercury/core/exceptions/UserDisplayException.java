package com.mercury.core.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDisplayException extends RuntimeException {
    private final ErrorCode code;

    public UserDisplayException(ErrorCode code) {
        this.code = code;
    }
}
