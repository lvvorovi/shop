package com.shop.domains.products.productMappers;

import com.shop.domains.products.ProductDto;
import com.shop.domains.products.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductPageMapper {

    private final ProductMapper productMapper;

    public ProductPageMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Page<ProductDto> toDtoPage(Page<ProductEntity> entityPage) {
        return entityPage.map(productMapper::toDto);
    }

    public Page<ProductEntity> toEntityPage(Page<ProductDto> dtoPage) {
        return dtoPage.map(productMapper::toEntity);
    }



}
