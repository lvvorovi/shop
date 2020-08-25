package com.shop.domains.userItems.userItemService.validation.exceptions;

import com.shop.core.exceptions.ValidationException;

abstract class UserItemValidationException extends ValidationException {

    public UserItemValidationException(String message) {
        super(message);
    }
}
