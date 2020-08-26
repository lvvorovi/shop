package com.shop.domains.userItems.userItemService.validation.rules;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemUserException;
import org.springframework.stereotype.Component;

@Component
public class UserItemUserRule implements UserItemValidationRule {

    @Override
    public void validate(UserItemDto dto) {
        validateNotNull(dto);

        if (dto.getUser() == null) {
            throw new UserItemUserException("User in ProductList should be not null");
        }

    }
}
