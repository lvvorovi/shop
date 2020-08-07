package com.shop.domains.userItems.productListService.validation.rules;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.productListService.validation.exceptions.ProductListProductException;
import org.springframework.stereotype.Component;

@Component
public class ProductListProductRule implements ProductListValidationRule {

    @Override
    public void validate(UserItemDto dto) {
        validateNotNull(dto);

        if (dto.getProduct().equals(null)) {
            throw new ProductListProductException("Product in ProductList should be not null");
        }
    }
}
