package com.shop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.ProductService;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ArrayList<ProductDto> findAll() {
        return productService.findAll();
    }

    @PostMapping("/{dtoJson}")
    public void save(@PathVariable String dtoJson) throws IOException {
        ProductDto dto = new ObjectMapper().readValue(dtoJson, ProductDto.class);

        productService.save(dto);
    }

}
