package com.shop.domains.products.productService;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.ProductEntity;
import com.shop.domains.products.ProductMapper;
import com.shop.domains.products.ProductRepositoryJpa;
import com.shop.domains.products.productService.validation.ProductValidationService;
import com.shop.domains.products.productService.validation.exceptions.ProductNotFoundException;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final ProductRepositoryJpa productRepository;
    private final ProductMapper productMapper;
    private final ProductValidationService productValidationService;

    public ProductService(ProductRepositoryJpa productRepository, ProductMapper productMapper, ProductValidationService productValidationService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productValidationService = productValidationService;
    }

    public ProductDto save(ProductDto dto) {
        productValidationService.validate(dto);
        return productMapper.toDto(productRepository.save(productMapper.toEntity(dto)));
    }

    public ArrayList<ProductDto> saveAll(@NotNull ArrayList<ProductDto> dtoList) {
        dtoList.forEach(productValidationService::validate);
        ArrayList<ProductEntity> entityList = new ArrayList<>();
        dtoList.forEach(dto -> entityList.add(productMapper.toEntity(dto)));
        ArrayList<ProductEntity> returnedEntityList = (ArrayList<ProductEntity>) productRepository.saveAll(entityList);
        dtoList.clear();
        returnedEntityList.forEach(entity -> dtoList.add(productMapper.toDto(entity)));
        return dtoList;
    }

    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found")));
    }

    public ArrayList<ProductDto> findAll() {
        ArrayList<ProductDto> dtoList = new ArrayList<>();
        productRepository.findAll().forEach(entity -> dtoList.add(productMapper.toDto(entity)));
        return dtoList;
    }

    public void delete(ProductDto dto) {
        productValidationService.validate(dto);
        productRepository.delete(productMapper.toEntity(dto));
    }

    public void deleteAll(@NotNull ArrayList<ProductDto> dtoList) {
        dtoList.forEach(productValidationService::validate);
        ArrayList<ProductEntity> entityList = new ArrayList<>();
        dtoList.forEach(dto -> entityList.add(productMapper.toEntity(dto)));
        productRepository.deleteAll(entityList);


    }
}
