package com.shop.domains.userItems.userItemService;

import com.shop.core.beanMappers.BeanMapperService;
import com.shop.domains.products.productService.ProductService;
import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.UserItemEntity;
import com.shop.domains.userItems.UserItemRepository;
import com.shop.domains.userItems.userItemService.validation.UserItemValidationService;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemAlreadyExistsException;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemNotFoundException;
import com.shop.domains.users.UserDto;
import com.shop.domains.users.UserEntity;
import com.shop.domains.users.userService.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

/*
    private UserItemDto getDto(Long userId, Long productId, int quantity) {
        UserDto userDto = userService.findById(userId);
        ProductDto productDto = productService.findById(productId);
        UserItemDto dto = new UserItemDto();
        dto.setUser(userDto);
        dto.setProduct(productDto);
        dto.setQuantity(quantity);
        return dto;
    }
*/
// TODO divide save from update

    public void save(UserItemDto dto) {
        userItemValidationService.validate(dto);
        UserItemEntity entity = beanMappers.getUserItemMapper().toEntity(dto);

        if (userItemRepository.findByUserAndProduct(entity.getUser(), entity.getProduct()).isPresent()) {
            throw new UserItemAlreadyExistsException("Product with id "
                    + entity.getProduct().getId() + " already in the basket");
        }
        userItemRepository.save(entity);
    }

    public void update(UserItemDto dto) {
        userItemValidationService.validate(dto);
        UserItemEntity entity = beanMappers.getUserItemMapper().toEntity(dto);

        if (dto.getId() == null || userItemRepository
                .findByUserAndProduct(entity.getUser(), entity.getProduct()).isEmpty()) {
            throw new UserItemNotFoundException("Product with id "
                    + dto.getProduct().getId() + " not in the basket");
        }
        userItemRepository.save(entity);
    }

    public Page<UserItemDto> findAllByUserId(Long userId, Pageable pageable) {
        UserDto userDto = userService.findById(userId);
        UserEntity userEntity = beanMappers.getUserMapper().toEntity(userDto);
        Page<UserItemEntity> entityPage = userItemRepository.findAllByUser(userEntity, pageable);
        return (Page<UserItemDto>) entityPage.stream()
                .map(beanMappers.getUserItemMapper()::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (userItemRepository.findById(id).isEmpty()) {
            throw new UserItemNotFoundException("Product with id "
                    + id + " not in the basket");
        }
        userItemRepository.deleteById(id);
    }

/*    public void deleteAll(Long userId) {
        userItemRepository.deleteAll(userId);
    }*/

}
