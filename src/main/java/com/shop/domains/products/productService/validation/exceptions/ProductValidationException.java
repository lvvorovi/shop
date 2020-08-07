package com.shop.domains.products.productService.validation.exceptions;

import com.shop.domains.ValidationException;

public class ProductValidationException extends ValidationException {

    public ProductValidationException(String message) {
        super(message);
    }

}
