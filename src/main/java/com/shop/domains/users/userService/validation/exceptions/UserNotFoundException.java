package com.shop.domains.users.userService.validation.exceptions;

public class UserNotFoundException extends UserValidationException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
