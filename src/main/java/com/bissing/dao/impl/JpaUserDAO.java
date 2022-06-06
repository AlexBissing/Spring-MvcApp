package com.bissing.dao.impl;

import com.bissing.dao.UserDAO;
import com.bissing.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    public User getOne(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.email = :email", User.class
        );
        query.setParameter("email", email);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }
}
