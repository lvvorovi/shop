package com.shop.domains.userItems.userItemService.validation.rules;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemNullException;

public interface UserItemValidationRule {

    void validate(UserItemDto dto);

    default void validateNotNull(UserItemDto dto) {
        if (dto == null) {
            throw new UserItemNullException("ProductList should not be null");
        }
    }

}
