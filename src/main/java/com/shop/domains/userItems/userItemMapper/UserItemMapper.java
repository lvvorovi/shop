package com.shop.domains.userItems.userItemMapper;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.ProductEntity;
import com.shop.domains.products.ProductMapper;
import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.UserItemEntity;
import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserEntity;
import com.shop.domains.users.userMappers.UserMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserItemMapper {

    private final ProductMapper productMapper;
    private final UserMapper userMapper;

    public UserItemMapper(ProductMapper productMapper, UserMapper userMapper) {
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }

    public UserItemEntity toEntity(UserItemDto dto) {
        UserItemEntity entity = new UserItemEntity();
        entity.setId(dto.getId());
        UserEntity userEntity = userMapper.toEntity(dto.getUser());
        entity.setUser(userEntity);
        ProductEntity productEntity = productMapper.toEntity(dto.getProduct());
        entity.setProduct(productEntity);
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

    public UserItemDto toDto(UserItemEntity entity) {
        UserItemDto dto = new UserItemDto();
        dto.setId(entity.getId());
        UserDto userDto = userMapper.toDto(entity.getUser());
        dto.setUser(userDto);
        ProductDto productDto = productMapper.toDto(entity.getProduct());
        dto.setProduct(productDto);
        dto.setQuantity(entity.getQuantity());
        dto.setTotalPrice(productDto.getActualPrice().multiply(new BigDecimal(dto.getQuantity())));
        return dto;
    }

}
