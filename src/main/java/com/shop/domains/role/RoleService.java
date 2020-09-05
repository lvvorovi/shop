package com.shop.domains.role;

import com.shop.domains.role.validation.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(RoleEntity entity) {
        roleRepository.save(entity);
    }

    public RoleEntity findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role with name " + name + " not found"));
    }

    public void delete(RoleEntity entity) {
        roleRepository.delete(entity);
    }


}
