package com.shop.domains.users.userService.validation.exceptions;

import com.shop.core.exceptions.ValidationException;

public class UserValidationException extends ValidationException {

    public UserValidationException(String message) {
        super(message);
    }
}
