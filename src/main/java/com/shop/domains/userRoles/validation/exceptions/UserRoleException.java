package com.shop.domains.userRoles.validation.exceptions;

import com.shop.core.exceptions.ValidationException;

public class UserRoleException extends ValidationException {

    public UserRoleException(String message) {
        super(message);
    }
}
