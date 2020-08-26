package com.shop.controllers;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.UserItemService;
import com.shop.domains.users.UserDto;
import com.shop.domains.users.userService.UserService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserItemService userItemService;

    public UserController(UserService userService, UserItemService userItemService) {
        this.userService = userService;
        this.userItemService = userItemService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/items")
    public List<UserItemDto> getItemListFromUserBasket(@PathVariable Long id) {
        List<UserItemDto> userItemDtoList = userItemService.findAllByUserId(id);

        for (final UserItemDto userItem : userItemDtoList) {
            Link linkToProductDto = linkTo(methodOn(ProductController.class)
                    .findById(userItem.getProduct().getId())).withRel("allProducts");
            userItem.getProduct().add(linkToProductDto);
        }

        return userItemDtoList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@Validated @RequestBody UserDto dto) {
        userService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@Validated @RequestBody UserDto dto) {
        userService.delete(dto);
    }

}
