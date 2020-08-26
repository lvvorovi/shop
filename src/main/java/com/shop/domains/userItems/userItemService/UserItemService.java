package com.shop.domains.userItems.userItemService;

import com.shop.domains.beanMappers.BeanMapperService;
import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.ProductService;
import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.UserItemEntity;
import com.shop.domains.userItems.UserItemRepository;
import com.shop.domains.userItems.userItemMapper.UserItemMapper;
import com.shop.domains.userItems.userItemService.validation.UserItemValidationService;
import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserEntity;
import com.shop.domains.users.userService.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserItemService {

    private final UserItemRepository userItemRepository;
    private final UserItemValidationService userItemValidationService;
    private final BeanMapperService beanMappers;
    private final UserService userService;
    private final ProductService productService;

    public UserItemService(UserItemRepository userItemRepository,
                           UserItemValidationService userItemValidationService,
                           BeanMapperService beanMappers,
                           UserService userService,
                           ProductService productService) {
        this.userItemRepository = userItemRepository;
        this.userItemValidationService = userItemValidationService;
        this.beanMappers = beanMappers;
        this.productService = productService;
        this.userService = userService;
    }

    private UserItemDto getDto(Long userId, Long productId, int quantity) {
        UserDto userDto = userService.findById(userId);
        ProductDto productDto = productService.findById(productId);
        UserItemDto dto = new UserItemDto();
        dto.setUser(userDto);
        dto.setProduct(productDto);
        dto.setQuantity(quantity);
        return dto;
    }

/*    public UserItemDto save(Long userId, Long productId, int quantity) {
        UserItemDto dto = getDto(userId, productId, quantity);
        userItemValidationService.validate(dto);
        UserItemEntity entity = beanMappers.getProductListMapper().toEntity(dto);

*//*        UserItemEntity returnedEntity = userItemRepository.find(entity.getUser(), entity.getProduct()).orElse(entity);
        UserItemEntity savedEntity;
        if (returnedEntity.getId() == null) {
            savedEntity = userItemRepository.save(returnedEntity);
        } else {
            returnedEntity.setQuantity(returnedEntity.getQuantity() + quantity);
            savedEntity = userItemRepository.changeQuantity(returnedEntity);
        }*//*
        return beanMappers.getProductListMapper().toDto(savedEntity);
    }*/

/*    public UserItemDto changeQuantity(Long userId, Long productId, int quantity) {
        UserItemDto dto = getDto(userId, productId, quantity);
        userITem.validate(dto);
        UserItemEntity entity = beanMappers.getProductListMapper().toEntity(dto);
        UserItemEntity returnedEntity = userItemRepository.getIfPresent(entity.getUser(), entity.getProduct()).orElse(entity);
        returnedEntity.setQuantity(quantity);
        UserItemEntity savedEntity = userItemRepository.changeQuantity(returnedEntity);
        return beanMappers.getProductListMapper().toDto(savedEntity);
    }*/

    public List<UserItemDto> findAllByUserId(Long userId) {
        UserDto userDto = userService.findById(userId);
        UserEntity userEntity = beanMappers.getUserMapper().toEntity(userDto);

        List<UserItemEntity> entityList = userItemRepository.findAllByUser(userEntity).orElseGet(ArrayList::new);
        return entityList.stream().map(beanMappers.getProductListMapper()::toDto).collect(Collectors.toList());
    }

/*    public void delete(Long userId, Long productId, int quantity) {
        UserItemDto dto = getDto(userId, productId, quantity);
        userITem.validate(dto);
        UserItemEntity entity = beanMappers.getProductListMapper().toEntity(dto);

        UserItemEntity returnedEntity = userItemRepository.getIfPresent(entity.getUser(), entity.getProduct()).orElse(entity);
        if (returnedEntity.getId() == null) {
            throw new ProductListNotFoundException("Product with ID " + productId + " was not found in basket of user with ID " + userId);
        }
        userItemRepository.delete(beanMappers.getProductListMapper().toEntity(dto));
    }*/

/*    public void deleteAll(Long userId) {
        System.out.println(userService.findById(userId));
        userItemRepository.deleteAll(userId);
    }*/

}
