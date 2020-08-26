package com.shop.domains.userItems.userItemService.validation.rules;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemProductException;
import org.springframework.stereotype.Component;

@Component
public class UserItemProductRule implements UserItemValidationRule {

    @Override
    public void validate(UserItemDto dto) {
        validateNotNull(dto);

        if (dto.getProduct().equals(null)) {
            throw new UserItemProductException("Product in ProductList should be not null");
        }
    }
}
