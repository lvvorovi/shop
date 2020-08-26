package com.shop.domains.products.productService;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.ProductEntity;
import com.shop.domains.products.productMappers.ProductMapper;
import com.shop.domains.products.ProductRepository;
import com.shop.domains.products.productMappers.ProductPageMapper;
import com.shop.domains.products.productService.validation.ProductValidationService;
import com.shop.domains.products.productService.validation.exceptions.ProductNotFoundException;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductValidationService productValidationService;
    private final ProductPageMapper productPageMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductValidationService productValidationService, ProductPageMapper productPageMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productValidationService = productValidationService;
        this.productPageMapper = productPageMapper;
    }

    public ProductDto save(ProductDto dto) {
        productValidationService.validate(dto);
        return productMapper.toDto(productRepository.saveAndFlush(productMapper.toEntity(dto)));
    }

    public ArrayList<ProductDto> findAll() {
        ArrayList<ProductDto> dtoList = new ArrayList<>();
        productRepository.findAll().forEach(entity -> dtoList.add(productMapper.toDto(entity)));
        return dtoList;
    }

    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found")));
    }

    public Page<ProductDto> findAllPagedAndSorted(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }


    public void delete(ProductDto dto) {
        productValidationService.validate(dto);
        productRepository.delete(productMapper.toEntity(dto));
    }

    public ProductDto update(ProductDto dto) {
        productValidationService.validate(dto);
        if (productRepository.findById(dto.getId()).isPresent()) {
            return productMapper.toDto(productRepository.saveAndFlush(productMapper.toEntity(dto)));
        } else {
            throw new ProductNotFoundException("No product with id " + dto.getId() + " was found");
        }
    }

}
