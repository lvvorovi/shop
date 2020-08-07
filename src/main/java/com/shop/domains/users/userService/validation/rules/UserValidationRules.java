package com.shop.domains.users.userService.validation.rules;

import com.shop.domains.users.UserDto;
import com.shop.domains.users.userService.validation.exceptions.UserNullException;
import org.springframework.stereotype.Component;

@Component
public interface UserValidationRules {

    void validate(UserDto dto);

    default void notNull(UserDto dto) {
        if (dto == null) {
            throw new UserNullException("User should be not null");
        }
    }

}
