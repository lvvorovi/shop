package com.shop.controllers;

import com.shop.domains.userItems.UserItemDto;
import com.shop.domains.userItems.userItemService.UserItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/items")
public class UserItemController {

    private final UserItemService userItemService;

    public UserItemController(UserItemService userItemService) {
        this.userItemService = userItemService;
    }


/*    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/items")
    public List<UserItemDto> getAllById(@PathVariable Long id) {
        List<UserItemDto> userItemDtoList = userItemService.findAllByUserId(id);

        for (UserItemDto userItem : userItemDtoList) {
            Link linkToProductDto = linkTo(methodOn(ProductController.class)
                    .findById(userItem.getProduct().getId())).withRel("allProducts");
            userItem.getProduct().add(linkToProductDto);
        }

        return userItemDtoList;
    }*/

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Page<UserItemDto> getAllByUserId(@PathVariable Long id, @RequestBody Pageable pageable) {
        Page<UserItemDto> dtoPage = userItemService.findAllByUserId(id, pageable);

        for (UserItemDto userItem : dtoPage) {
            Link linkToProductDto = linkTo(methodOn(ProductController.class)
                    .findById(userItem.getProduct().getId())).withRel("allProducts");
            userItem.getProduct().add(linkToProductDto);
        }

        return dtoPage;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addToBasket(@RequestBody UserItemDto dto) {
        userItemService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateInBasket(@RequestBody UserItemDto dto) {
        userItemService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFromBasket(Long id) {
        userItemService.delete(id);
    }

}


