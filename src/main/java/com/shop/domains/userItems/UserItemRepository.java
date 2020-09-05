package com.shop.domains.userItems;


import com.shop.domains.products.ProductEntity;
import com.shop.domains.users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserItemRepository extends JpaRepository<UserItemEntity, Long> {

//    List<UserItemEntity> findAllByUser(UserEntity entity);

    Page<UserItemEntity> findAllByUser(UserEntity entity, Pageable pageable);

    Optional<UserItemEntity> findByUserAndProduct(UserEntity userEntity, ProductEntity productEntity);

}