package com.shop.domains.userItems.userItemService;

import com.shop.core.beanMappers.BeanMapperService;
import com.shop.domains.authentication.JwtUtil;
import com.shop.domains.products.productService.ProductService;
import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.UserItemEntity;
import com.shop.domains.userItems.UserItemRepository;
import com.shop.domains.userItems.userItemService.validation.UserItemValidationService;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemAlreadyExistsException;
import com.shop.domains.userItems.userItemService.validation.exceptions.UserItemNotFoundException;
import com.shop.domains.users.UserEntity;
import com.shop.domains.users.userService.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private final JwtUtil jwtUtil;

    public UserItemService(UserItemRepository userItemRepository,
                           UserItemValidationService userItemValidationService,
                           BeanMapperService beanMappers,
                           UserService userService,
                           ProductService productService, JwtUtil jwtUtil) {
        this.userItemRepository = userItemRepository;
        this.userItemValidationService = userItemValidationService;
        this.beanMappers = beanMappers;
        this.productService = productService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
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

    public Page<UserItemDto> findAllByUserId(Pageable pageable, HttpServletRequest request) {
//        UserDto userDto = userService.findById(userId);
//        UserEntity userEntity = beanMappers.getUserMapper().toEntity(userDto);
        UserEntity userEntity = getUserFromRequest(request);

        Page<UserItemEntity> entityPage = userItemRepository.findAllByUser(userEntity, pageable);
        List<UserItemDto> dtoList = entityPage.stream()
                .map(beanMappers.getUserItemMapper()::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList);
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

    private UserEntity getUserFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUserName(jwt);
        }

        return beanMappers.getUserMapper().toEntity(userService.findByEmail(username));

    }

}
