package com.shop.domains.userItems.productListService.validation.rules;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.productListService.validation.exceptions.ProductListQuantityException;
import org.springframework.stereotype.Component;

@Component
public class ProductListQuantityRule implements ProductListValidationRule {

    @Override
    public void validate(UserItemDto dto) {
        validateNotNull(dto);

        Integer quantity = dto.getQuantity();
        if (quantity == null) {
            throw new ProductListQuantityException("Quantity for ProductList should be not null");
        }
        if (quantity < 1) {
            throw new ProductListQuantityException("Quantity for ProductList should be at least 1");
        }
    }
}
