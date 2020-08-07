package com.shop.domains.products.productService;

import com.shop.domains.products.ProductEntity;
import com.shop.domains.products.ProductDto;
import com.shop.domains.products.productMappers.ProductMapper;
import com.shop.domains.products.productRepository.ProductRepository;
import com.shop.domains.products.productService.validation.ProductValidationService;
import com.shop.domains.products.productService.validation.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("hibernate")
public class ProductServiceHibernate {

    final ProductRepository productRepository;
    final ProductValidationService validationService;
    final ProductMapper productMapper;

    public ProductServiceHibernate(ProductRepository productRepository,
                                   ProductValidationService validationService,
                                   ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.validationService = validationService;
        this.productMapper = productMapper;
    }

    public ProductDto save(ProductDto dto) {
        validationService.validate(dto);
        ProductEntity entity = productRepository.save(productMapper.toEntity(dto));
        return productMapper.toDto(entity);
    }

    public ProductDto findById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return productMapper.toDto(entity);
    }

    public ArrayList<ProductDto> findAll() {
        List<ProductEntity> entityList = productRepository.findAll()
                .orElseThrow(() -> new ProductNotFoundException("No Products found"));
        ArrayList<ProductDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(productMapper.toDto(entity)));
        return dtoList;
    }

    public ProductDto update(ProductDto dto) {
        validationService.validate(dto);
        ProductEntity entity = productRepository.findById(dto.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + dto.getId() + " not found"));
        entity = productMapper.toEntity(dto);
        entity.setId(dto.getId());
        return productMapper.toDto(productRepository.update(entity));
    }

    public void deleteById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        productRepository.delete(entity);
    }

}
