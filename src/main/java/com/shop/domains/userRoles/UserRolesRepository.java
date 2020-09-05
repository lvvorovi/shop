package com.shop.domains.userRoles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {

    public Optional<List<UserRolesEntity>> findAllByUserId(Long userId);

}
