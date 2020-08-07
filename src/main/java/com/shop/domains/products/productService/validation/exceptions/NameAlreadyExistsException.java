package com.shop.domains.products.productService.validation.exceptions;

public class NameAlreadyExistsException extends ProductValidationException {

    public NameAlreadyExistsException(String message) {
        super(message);
    }

}
