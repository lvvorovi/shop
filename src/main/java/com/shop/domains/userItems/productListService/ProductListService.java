/*
package com.shop.domains.userItems.productListService;

import com.shop.domains.beanMappers.BeanMapperService;
import com.javaguru.shoppinglist.domains.userItems.UserItemEntity;
import com.javaguru.shoppinglist.domains.userItems.UserItemDto;
import com.javaguru.shoppinglist.domains.userItems.productListRepository.ProductListRepository;
import com.javaguru.shoppinglist.domains.userItems.productListService.validation.ProductListValidationService;
import com.javaguru.shoppinglist.domains.userItems.productListService.validation.exceptions.ProductListNotFoundException;
import com.javaguru.shoppinglist.domains.products.productDto.ProductDto;
import com.javaguru.shoppinglist.domains.products.productService.ProductService;
import com.javaguru.shoppinglist.domains.users.UserEntity;
import com.javaguru.shoppinglist.domains.users.UserDto;
import com.javaguru.shoppinglist.domains.users.userService.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductListService {

    private final ProductListRepository productListRepository;
    private final ProductListValidationService productListValidationService;
    private final BeanMapperService beanMappers;
    private final UserService userService;
    private final ProductService productService;

    public ProductListService(ProductListRepository productListRepository,
                              ProductListValidationService productListValidationService,
                              BeanMapperService beanMappers,
                              UserService userService,
                              ProductService productService) {
        this.productListRepository = productListRepository;
        this.productListValidationService = productListValidationService;
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

    public UserItemDto save(Long userId, Long productId, int quantity) {
        UserItemDto dto = getDto(userId, productId, quantity);
        productListValidationService.validate(dto);
        UserItemEntity entity = beanMappers.getProductListMapper().toEntity(dto);

        UserItemEntity returnedEntity = productListRepository.getIfPresent(entity.getUser(), entity.getProduct()).orElse(entity);
        UserItemEntity savedEntity;
        if (returnedEntity.getId() == null) {
            savedEntity = productListRepository.save(returnedEntity);
        } else {
            returnedEntity.setQuantity(returnedEntity.getQuantity() + quantity);
            savedEntity = productListRepository.changeQuantity(returnedEntity);
        }
        return beanMappers.getProductListMapper().toDto(savedEntity);
    }

    public UserItemDto changeQuantity(Long userId, Long productId, int quantity) {
        UserItemDto dto = getDto(userId, productId, quantity);
        productListValidationService.validate(dto);
        UserItemEntity entity = beanMappers.getProductListMapper().toEntity(dto);
        UserItemEntity returnedEntity = productListRepository.getIfPresent(entity.getUser(), entity.getProduct()).orElse(entity);
        returnedEntity.setQuantity(quantity);
        UserItemEntity savedEntity = productListRepository.changeQuantity(returnedEntity);
        return beanMappers.getProductListMapper().toDto(savedEntity);
    }

    public List<UserItemDto> findByUserId(Long userId) {
        UserDto userDto = userService.findById(userId);
        UserEntity userEntity = beanMappers.getUserMapper().toEntity(userDto);

        List<UserItemEntity> entityList = productListRepository.findByUserId(userEntity).orElseGet(ArrayList::new);
        List<UserItemDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(beanMappers.getProductListMapper().toDto(entity)));
        return dtoList;
    }

    public void delete(Long userId, Long productId, int quantity) {
        UserItemDto dto = getDto(userId, productId, quantity);
        productListValidationService.validate(dto);
        UserItemEntity entity = beanMappers.getProductListMapper().toEntity(dto);

        UserItemEntity returnedEntity = productListRepository.getIfPresent(entity.getUser(), entity.getProduct()).orElse(entity);
        if (returnedEntity.getId() == null) {
            throw new ProductListNotFoundException("Product with ID " + productId + " was not found in basket of user with ID " + userId);
        }
        productListRepository.delete(beanMappers.getProductListMapper().toEntity(dto));
    }

    public void deleteAll(Long userId) {
        System.out.println(userService.findById(userId));
        productListRepository.deleteAll(userId);
    }

}
*/
