package com.shop.domains.users.userService.validation.rules;

import com.shop.domains.users.UserDto;
import com.shop.domains.users.userService.validation.exceptions.UserNameException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class UserNameValidationRule implements UserValidationRules {

    @Override
    public void validate(UserDto dto) {
        notNull(dto);

        if (dto.getName() == null) {
            throw new UserNameException("Name should be not null");
        }
        if (dto.getName().length() > 25 || dto.getName().length() < 2) {
            throw new UserNameException("Name should be between 2 and 25 characters");
        }
    }
}
