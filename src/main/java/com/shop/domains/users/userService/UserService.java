package com.shop.domains.users.userService;

import com.shop.domains.users.UserEntity;
import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserRepository;
import com.shop.domains.users.userMappers.UserMapper;
import com.shop.domains.users.userService.validation.UserValidationService;
import com.shop.domains.users.userService.validation.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidationService validationService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserValidationService validationService,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.userMapper = userMapper;
    }

    public UserDto save(UserDto dto) {
        validationService.validate(dto);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(dto)));
    }

    public List<UserDto> findAll() {
        List<UserEntity> entityList = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(userMapper.toDto(entity)));
        return dtoList;
    }

/*    public UserDto update(UserDto dto) {
        validationService.validate(dto);
        UserEntity entity = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + dto.getId() + " not found"));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        return userMapper.toDto(userRepository.(entity));
    }*/

    public void delete(UserDto dto) {
        UserEntity entity = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + dto.getId() + " not found"));
        userRepository.delete(entity);
    }

    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found")));
    }

    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

}
