package com.shop.core.beanMappers;


import com.shop.domains.products.productMappers.ProductMapper;
import com.shop.domains.userItems.userItemMapper.UserItemMapper;
import com.shop.domains.users.userMappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class BeanMapperService {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final UserItemMapper userItemMapper;

    public BeanMapperService(UserMapper userMapper, ProductMapper productMapper, UserItemMapper userItemMapper) {
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.userItemMapper = userItemMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    public UserItemMapper getUserItemMapper() {
        return userItemMapper;
    }

}
