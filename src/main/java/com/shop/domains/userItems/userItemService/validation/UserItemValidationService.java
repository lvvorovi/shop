package com.shop.domains.userItems.userItemService.validation;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.validation.rules.UserItemValidationRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserItemValidationService {

    private final List<UserItemValidationRule> productListValidationRules;

    public UserItemValidationService(List<UserItemValidationRule> productListValidationRules) {
        this.productListValidationRules = productListValidationRules;
    }

    public void validate(UserItemDto dto) {
        productListValidationRules.forEach(rule -> rule.validate(dto));
    }


}
