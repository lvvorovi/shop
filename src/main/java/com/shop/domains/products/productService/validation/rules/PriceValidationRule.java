package com.shop.domains.products.productService.validation.rules;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.validation.exceptions.PriceIllegalException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(2)
public class PriceValidationRule implements ProductValidationRule {

    @Override
    public void validate(ProductDto dto) {
        checkProductNotNull(dto);

        if (dto.getPrice() == null) {
            throw new IllegalArgumentException("Price should be not null");
        }
        if ((dto.getPrice().compareTo(new BigDecimal(0))) <= 0) {
            throw new PriceIllegalException("Price must be greater than 0");
        }
    }

}
