package com.shop.domains.products.productService.validation;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.validation.rules.ProductValidationRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductValidationService {

    private final List<ProductValidationRule> productValidationRules;

    public ProductValidationService(List<ProductValidationRule> productValidationRules) {
        this.productValidationRules = productValidationRules;
    }

    public void validate(ProductDto dto) {
        productValidationRules.forEach(rule -> rule.validate(dto));
    }

}
