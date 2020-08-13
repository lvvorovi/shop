package com.shop.domains.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepositoryJpa extends
        /*CrudRepository<ProductEntity, Long>, */
        /*PagingAndSortingRepository<ProductEntity, Long>*/
        JpaRepository<ProductEntity, Long> {

//            List<ProductEntity> findAllByName(String name, Pageable pageable);

}
