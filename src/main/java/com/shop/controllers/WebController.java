/*
package com.shop.controllers;


import com.shop.domains.products.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(ProductDto productDto) {
        return "ProductForm";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid ProductDto productDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ProductForm";
        }

        return "redirect:/products";
    }
}*/
