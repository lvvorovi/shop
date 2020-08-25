package com.shop.controllers;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ArrayList<ProductDto> findAll() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/paged")
    public Page<ProductDto> finalAllPaged(Pageable pageable) {
        return productService.findAllPagedAndSorted(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDto save(@Validated @RequestBody ProductDto dto) {
        return productService.save(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ProductDto update(@Validated @RequestBody ProductDto dto) {
        return productService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void delete(@Valid @RequestBody ProductDto dto) {
        productService.delete(dto);
    }

}


