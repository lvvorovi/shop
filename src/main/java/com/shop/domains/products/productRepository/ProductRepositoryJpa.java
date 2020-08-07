package com.shop.domains.products.productRepository;

import com.shop.domains.products.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryJpa extends CrudRepository<ProductEntity, Long> {
}
