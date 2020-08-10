package com.shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.ProductService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/{dtoJson}")
    public void save(@PathVariable String dtoJson) throws IOException {
        ProductDto dto = new ObjectMapper().readValue(dtoJson, ProductDto.class);
        productService.save(dto);
    }

    @PostMapping("/bulk/{jsonList}")
    public void saveAll(@PathVariable String jsonList) throws IOException {
        ArrayList<ProductDto> dtoList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray("[" + jsonList + "]");
        jsonArray.forEach(json -> {
            try {
                dtoList.add(objectMapper.readValue(json.toString(), ProductDto.class));
            } catch (JsonProcessingException e) {
                e.getMessage();
            }
        });
        productService.saveAll(dtoList);
    }


}
