package com.shop.domains.userItems.userItemService.validation.rules;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemQuantityException;
import org.springframework.stereotype.Component;

@Component
public class UserItemQuantityRule implements UserItemValidationRule {

    @Override
    public void validate(UserItemDto dto) {
        validateNotNull(dto);

        Integer quantity = dto.getQuantity();
        if (quantity == null) {
            throw new UserItemQuantityException("Quantity for ProductList should be not null");
        }
        if (quantity < 1) {
            throw new UserItemQuantityException("Quantity for ProductList should be at least 1");
        }
    }
}
