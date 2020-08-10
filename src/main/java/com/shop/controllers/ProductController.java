package com.shop.controllers;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}")
    @ResponseBody
    public ProductDto findById(@PathVariable String id) {
        return productService.findById(Long.parseLong(id));
    }

    @PostMapping
    public void save(@RequestBody ProductDto dto) {
        productService.save(dto);
    }

    @PostMapping("/bulk")
    public void saveAll(@RequestBody List<ProductDto> dtoList) {
        System.out.println(dtoList);
        productService.saveAll((ArrayList<ProductDto>) dtoList);
    }
}
