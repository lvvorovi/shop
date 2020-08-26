package com.shop.domains.products.productService.validation.exceptions;

import com.shop.core.exceptions.ValidationException;

public class ProductValidationException extends ValidationException {

    public ProductValidationException(String message) {
        super(message);
    }

}
