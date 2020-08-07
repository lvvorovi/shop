package com.shop.domains.users.userService.validation;

import com.shop.domains.users.UserDto;
import com.shop.domains.users.userService.validation.rules.UserValidationRules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserValidationService {

    private final List<UserValidationRules> userValidationRules;

    public UserValidationService(List<UserValidationRules> userValidationRules) {
        this.userValidationRules = userValidationRules;
    }

    public void validate(UserDto dto) {
        userValidationRules.forEach(rule -> rule.validate(dto));
    }
}
