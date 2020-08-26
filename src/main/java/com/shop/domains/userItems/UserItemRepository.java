package com.shop.domains.userItems;


import com.shop.domains.userItems.UserItemEntity;
import com.shop.domains.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserItemRepository extends JpaRepository<UserItemEntity, Long> {

    Optional<List<UserItemEntity>> findAllByUser (UserEntity entity);

}