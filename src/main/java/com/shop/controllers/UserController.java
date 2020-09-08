package com.shop.controllers;

import com.shop.domains.users.UserDto;
import com.shop.domains.users.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    @GetMapping("/{email}")
    public UserDto findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@Validated @RequestBody UserDto dto) {
        userService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void update(@Validated @RequestBody UserDto dto) {
        userService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@Validated @RequestBody UserDto dto) {
        userService.delete(dto);
    }

}
