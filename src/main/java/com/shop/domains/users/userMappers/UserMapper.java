package com.shop.domains.users.userMappers;

import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        dto.setLastName(entity.getLastName());
        dto.setEnabled(entity.getEnabled());
        dto.setCredentialsNotExpired(entity.getCredentialsNotExpired());
        dto.setAccountNonExpired(entity.getAccountNonExpired());
        dto.setAccountNonLocked(entity.getAccountNonLocked());
        dto.setRoles(entity.getRoles());
        dto.setUserName(entity.getUsername());

        return dto;
    }

    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setUpdated(dto.getUpdated());
        entity.setCreated(dto.getCreated());
        entity.setLastName(dto.getLastName());
        entity.setEnabled(dto.getEnabled());
        entity.setCredentialsNotExpired(dto.getCredentialsNotExpired());
        entity.setAccountNonLocked(dto.getAccountNonLocked());
        entity.setAccountNonExpired(dto.getAccountNonExpired());
        entity.setRoles(dto.getRoles());
        entity.setUsername(dto.getUserName());

        return entity;
    }

}
