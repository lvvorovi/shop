package com.shop.domains.users.userRepository;

import com.shop.domains.users.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserHibernateRepository {

    private final SessionFactory sessionFactory;

    public UserHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session dataBase() {
        return sessionFactory.getCurrentSession();
    }


    public UserEntity save(UserEntity entity) {
        dataBase().save(entity);
        return entity;
    }


    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(dataBase().find(UserEntity.class, id));
    }


    public Optional<List<UserEntity>> findAll() {
        List<UserEntity> entityList = dataBase().createCriteria(UserEntity.class).list();
        return !entityList.isEmpty() ? Optional.of(entityList) : Optional.empty();
    }


    public UserEntity update(UserEntity entity) {
        dataBase().update(entity);
        return entity;
    }


    public void delete(UserEntity entity) {
        dataBase().delete(entity);
    }


    public Boolean isById(Long id) {
        return dataBase().get(UserEntity.class, id) != null;
    }


    public Optional<UserEntity> findByName(String name) {
        UserEntity entity = (UserEntity) dataBase().createCriteria(UserEntity.class)
                .add(Restrictions.eq("name", name).ignoreCase()).uniqueResult();
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    public UserEntity findByEmail(String email) {
        return (UserEntity) dataBase().createCriteria(UserEntity.class)
                .add(Restrictions.eq("email", email).ignoreCase()).uniqueResult();
    }
}
