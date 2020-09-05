package com.shop.domains.role.validation.exceptions;

import com.shop.core.exceptions.ValidationException;

//TODO extend internalError500
public class RoleException extends ValidationException {

    public RoleException(String message) {
        super(message);
    }

}
