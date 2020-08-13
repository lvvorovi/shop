package com.shop.controllers;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productService.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.ArrayList;

//@RestController
@Controller
@RequestMapping("/products")
public class ProductController implements WebMvcConfigurer {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public ArrayList<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = {
            "/paged/?pageNr={pageNr}&pageSize={pageSize}",
            "/paged/?pageNr={pageNr}&pageSize={pageSize}&sortOrder={sortOrder}"
    }, produces = "application/json")
    public Page<ProductDto> finalAllPaged(@PathVariable int pageNr,
                                          @PathVariable int pageSize,
                                          @PathVariable(required = false) String sortOrder) {
        Pageable pageable;
        if (sortOrder == null) {
            pageable = PageRequest.of(pageNr - 1, pageSize, Sort.by("price"));
        } else {
            pageable = PageRequest.of(pageNr - 1, pageSize, Sort.by(sortOrder));
        }
        return productService.findAllPagedAndSorted(pageable);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public ProductDto findById(@PathVariable String id) {
        return productService.findById(Long.parseLong(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ProductDto save(@Valid @RequestBody ProductDto dto) {
        return productService.save(dto);
    }

    @GetMapping("/form")
    public String showForm(ProductDto productDto) {
        return "ProductForm";
    }

    @PostMapping("/form")
    public String checkPersonInfo(@Valid ProductDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ProductForm";
        }

        ProductDto returnedDto = productService.save(dto);
        return "redirect:/products/" + returnedDto.getId().toString();
    }

/*    @PostMapping(path = "/bulk", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public List<ProductDto> saveAll(@RequestBody List<ProductDto> dtoList) {
        System.out.println(dtoList);
        return productService.saveAll((ArrayList<ProductDto>) dtoList);
    }*/


}


