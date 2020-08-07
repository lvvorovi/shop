package com.shop.domains.products.productRepository;/*
package com.shop.domains.products.productRepository;

import com.shop.domains.products.ProductEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("inmemory")
public class ProductInMemoryRepository implements ProductRepository {

    private final Map<Long, ProductEntity> repository = new HashMap<>();
    private Long productIdSequence = 0L;

    @Override
    public ProductEntity save(ProductEntity entity) {
        entity.setId(productIdSequence++);
        return repository.put(entity.getId(), entity);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Optional<List<ProductEntity>> findAll() {
        return Optional.of((List<ProductEntity>) repository.values());
    }

    @Override
    public Boolean isByName(String name) {
        for (ProductEntity entry : repository.values()) {
            if (entry.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteById(Long id) {
        for (ProductEntity entry : repository.values()) {
            if (entry.getId().equals(id)) {
                repository.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductEntity updateById(Long id, ProductEntity entity) {
        return null;
    }

    @Override
    public Boolean isById(Long id) {
        return null;
    }
}
*/
