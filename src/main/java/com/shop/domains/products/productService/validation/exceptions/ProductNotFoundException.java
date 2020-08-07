package com.shop.domains.products.productService.validation.exceptions;

public class ProductNotFoundException extends ProductValidationException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}
