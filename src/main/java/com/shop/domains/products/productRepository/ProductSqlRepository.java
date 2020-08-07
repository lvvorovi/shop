package com.shop.domains.products.productRepository;/*
package com.shop.domains.products.productRepository;

import com.shop.domains.products.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql")
public class ProductSqlRepository implements ProductRepository {

    private final JdbcTemplate dataBase;

    public ProductSqlRepository(JdbcTemplate jdbcTemplate) {
        this.dataBase = jdbcTemplate;
    }

    private BeanPropertyRowMapper<ProductEntity> rowMapper() {
        return new BeanPropertyRowMapper<>(ProductEntity.class);
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        String sqlSaveProduct = "INSERT INTO products (name, price, discount, sku, description) VALUES (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        session().save(entity);
        dataBase.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveProduct, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBigDecimal(2, entity.getPrice());
            preparedStatement.setBigDecimal(3, entity.getDiscount());
            preparedStatement.setString(4, entity.getSku());
            preparedStatement.setString(5, entity.getDescription());
            return preparedStatement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());
        return entity;
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        String sqlGetProductById = "SELECT * FROM products WHERE id = ?";
        Object[] parameters = {id};
        int[] types = {Types.BIGINT};
        ArrayList<ProductEntity> entityList = (ArrayList<ProductEntity>) dataBase.query(sqlGetProductById, parameters, types, rowMapper());
        return entityList.size() > 0 ? Optional.of(entityList.get(0)) : Optional.empty();
    }

    @Override
    public Boolean isByName(String name) {
        String sqlIsProductByName = "SELECT COUNT(*) FROM products WHERE name = ?";
        Object[] parameters = {name};
        int[] types = {Types.VARCHAR};
        Integer i = dataBase.queryForObject(sqlIsProductByName, parameters, types, Integer.class);
        if (i != null) {
            return i > 0;
        }
        return false;
    }

    @Override
    public Optional<List<ProductEntity>> findAll() {
        String sqlFindAllProducts = "SELECT * FROM products";
        List<ProductEntity> entityList = dataBase.query(sqlFindAllProducts, rowMapper());
        return entityList.size() > 0 ? Optional.of(entityList) : Optional.empty();
    }

    @Override
    public Boolean isById(Long id) throws NullPointerException {
        String sqlIsProductById = "SELECT COUNT(*) FROM products WHERE id = ?";
        Object[] parameters = {id};
        int[] types = {Types.BIGINT};
        Integer i = dataBase.queryForObject(sqlIsProductById, parameters, types, Integer.class);
        if (i != null) {
            return i > 0;
        }
        return false;
    }

    @Override
    public ProductEntity updateById(Long id, ProductEntity entity) {
        String sqlUpdateProduct = "UPDATE products SET name = ?, price = ?, discount = ?, sku = ?, description = ? WHERE id = ?";

        dataBase.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(sqlUpdateProduct);
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setBigDecimal(3, entity.getDiscount());
            statement.setString(4, entity.getSku());
            statement.setString(5, entity.getDescription());
            statement.setLong(6, id);
            return statement;
        });

        entity.setId(id);
        return entity;
    }

    @Override
    public Boolean deleteById(Long id) {
        String deleteProductById = "DELETE FROM products WHERE id = " + id;
        return dataBase.update(deleteProductById) > 0;
    }

    public Session session() {
        Configuration configuration = new Configuration().configure("").addAnnotatedClass(ProductEntity.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }


}
*/
