/*
package com.shop.domains.userItems.productListRepository;

import com.javaguru.shoppinglist.domains.userItems.UserItemEntity;
import com.javaguru.shoppinglist.domains.products.ProductEntity;
import com.javaguru.shoppinglist.domains.users.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductListRepository {

    private final SessionFactory sessionFactory;

    public ProductListRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session dataBase() {
        return sessionFactory.getCurrentSession();
    }

    public UserItemEntity save(UserItemEntity entity) {
        Long generatedId = (Long) dataBase().save(entity);
        entity.setId(generatedId);
        return entity;
    }

    public UserItemEntity changeQuantity(UserItemEntity entity) {
        dataBase().merge(entity);
        return entity;
    }

*/
/*    public Boolean isPresent(ProductListEntity entity) {
        Criterion productId = Restrictions.eq("product", entity.getProduct().getId());
        Criterion userId = Restrictions.eq("user", entity.getUser().getId());
        entity = (ProductListEntity) dataBase().createCriteria(ProductListEntity.class)
                .add(Restrictions.conjunction().add(userId).add(productId)).uniqueResult();
        return entity != null;
    }*//*


    public Optional<UserItemEntity> getIfPresent(UserEntity userEntity, ProductEntity productEntity) {
        Criterion productIdCriterion = Restrictions.eq("product", productEntity);
        Criterion userIdCriterion = Restrictions.eq("user", userEntity);
        UserItemEntity entity = (UserItemEntity) dataBase().createCriteria(UserItemEntity.class)
                .add(Restrictions.conjunction().add(userIdCriterion).add(productIdCriterion)).uniqueResult();
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    public Optional<List<UserItemEntity>> findByUserId(UserEntity entity) {
        List<UserItemEntity> entityList = dataBase().createCriteria(UserItemEntity.class)
                .add(Restrictions.eq("user", entity)).list();
        return entityList.size() > 0 ? Optional.of(entityList) : Optional.empty();
    }

    public void delete(UserItemEntity entity) {
        dataBase().remove(entity);
    }

    public void deleteAll(Long userId) {
        Query query = dataBase().createQuery("DELETE UserItemEntity WHERE user = " + userId);
        query.executeUpdate();
    }


}
*/
