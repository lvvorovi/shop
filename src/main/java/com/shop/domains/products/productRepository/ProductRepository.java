package com.shop.domains.products.productRepository;

import com.shop.domains.products.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    ProductEntity save(ProductEntity entity);

    Optional<ProductEntity> findById(Long id);

    Optional<List<ProductEntity>> findAll();

    ProductEntity update(ProductEntity entity);

    void delete(ProductEntity entity);

    ProductEntity findByName(String name);

    Boolean isById(Long id);

}
