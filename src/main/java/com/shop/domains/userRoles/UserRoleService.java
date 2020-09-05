package com.shop.domains.userRoles;

import com.shop.domains.role.RoleEntity;
import com.shop.domains.role.RoleService;
import com.shop.domains.userRoles.validation.exceptions.UserRoleNotFoundException;
import com.shop.domains.users.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRolesRepository userRolesRepository;

    public UserRoleService(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public void save(UserEntity user, RoleEntity roles) {
        UserRolesEntity entity = new UserRolesEntity();
        entity.setRoles(roles);
        entity.setUser(user);
        userRolesRepository.save(entity);
    }

    public List<UserRolesEntity> findAllByUserId(Long userId) {
        return userRolesRepository.findAllByUserId(userId)
                .orElseThrow(() -> new UserRoleNotFoundException("User with id " + userId + " has no assigned roles"));
    }

}
