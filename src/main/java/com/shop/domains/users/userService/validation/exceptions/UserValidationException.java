package com.shop.domains.users.userService.validation.exceptions;

import com.shop.domains.ValidationException;

public class UserValidationException extends ValidationException {

    public UserValidationException(String message) {
        super(message);
    }
}
