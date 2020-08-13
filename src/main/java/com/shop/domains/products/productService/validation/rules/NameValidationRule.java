package com.shop.domains.products.productService.validation.rules;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.ProductEntity;
import com.shop.domains.products.ProductRepositoryJpa;
import com.shop.domains.products.productService.validation.exceptions.NameAlreadyExistsException;
import com.shop.domains.products.productService.validation.exceptions.NameIllegalException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(100)
public class NameValidationRule implements ProductValidationRule {

    private final ProductRepositoryJpa productRepository;

    public NameValidationRule(ProductRepositoryJpa productRepository) {
        this.productRepository = productRepository;
    }

    public void validate(ProductDto dto) {
        checkProductNotNull(dto);
        if (dto.getName() == null) {
            throw new IllegalArgumentException("Name should be not null");
        }
        if (dto.getName().length() < 3 || dto.getName().length() > 32) {
            throw new NameIllegalException("Name should be 3-32 characters long");
        }
    }
}
