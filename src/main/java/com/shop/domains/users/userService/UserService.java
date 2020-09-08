package com.shop.domains.users.userService;

import com.shop.domains.role.RoleService;
import com.shop.domains.userRoles.UserRoleService;
import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserEntity;
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
    private final UserRoleService userRoleService;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       UserValidationService validationService,
                       UserMapper userMapper,
                       UserRoleService userRoleService,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.userMapper = userMapper;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }

    public void save(UserDto dto) {
        validationService.validate(dto);

        dto.setAccountNonLocked(true);
        dto.setAccountNonExpired(true);
        dto.setCredentialsNotExpired(true);
        dto.setEnabled(true);

        UserEntity userEntity = userMapper.toEntity(dto);

        userMapper.toDto(userRepository.save(userEntity));
        userRoleService.save(userEntity, roleService.findByName("USER"));

    }

    public List<UserDto> findAll() {
        List<UserEntity> entityList = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(userMapper.toDto(entity)));
        return dtoList;
    }

    public void update(UserDto dto) {
        validationService.validate(dto);
        if (!existsById(dto.getId())) {
            throw new UserNotFoundException("User with id " + dto.getId() + " not found");
        }
        userMapper.toDto(userRepository.save(userMapper.toEntity(dto)));
    }

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

    public UserDto findByName(String firstName) {
        return userMapper.toDto(userRepository.findByFirstName(firstName)
                .orElseThrow(() -> new UserNotFoundException("User with name " + firstName + " not found")));
    }

    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with name " + email + " not found")));
    }

}
