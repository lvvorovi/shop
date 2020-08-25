package com.shop.domains.users.userService.validation.rules;

import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserEntity;
import com.shop.domains.users.UserRepository;
import com.shop.domains.users.userService.validation.exceptions.UserEmailException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
public class UserEmailValidationRule implements UserValidationRules {

    private final UserRepository userRepository;

    public UserEmailValidationRule(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserDto dto) {
        notNull(dto);

        if (dto.getEmail() == null) {
            throw new UserEmailException("Email should not be null");
        }
        if (dto.getEmail().isEmpty()) {
            throw new UserEmailException("Email should not be empty");
        }
        if (!dto.getEmail().contains("@")) {
            throw new UserEmailException("Illegal email format. Missing '@' symbol");
        }
        if (!dto.getEmail().contains(".")) {
            throw new UserEmailException("Illegal email format. Missing e.g. '.com' part");
        }
        if (dto.getEmail().startsWith("@")) {
            throw new UserEmailException("Illegal email format. Missing email username before '@' symbol");
        }
        UserEntity entity = userRepository.findByEmail(dto.getEmail()).orElseGet(UserEntity::new);
        if (entity.getId() != null && !entity.getEmail().equals(dto.getEmail())) {
            throw new UserEmailException("email already used by another user");
        }
    }
}
