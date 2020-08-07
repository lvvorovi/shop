package com.shop.domains.userItems.productListService.validation.exceptions;

import com.shop.domains.ValidationException;

abstract class ProductListValidationException extends ValidationException {

    public ProductListValidationException(String message) {
        super(message);
    }
}
