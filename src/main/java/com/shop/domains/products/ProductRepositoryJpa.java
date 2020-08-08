package com.shop.domains.products;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryJpa extends CrudRepository<ProductEntity, Long> {

}
