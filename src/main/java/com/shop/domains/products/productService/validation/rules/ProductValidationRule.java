package com.shop.domains.products.productService.validation.rules;

import com.shop.domains.products.ProductDto;

public interface ProductValidationRule {

    void validate(ProductDto productDto);

    default void checkProductNotNull(ProductDto productDto) {
        if (productDto == null) {
            throw new IllegalArgumentException("Product should be not null");
        }
    }

}
