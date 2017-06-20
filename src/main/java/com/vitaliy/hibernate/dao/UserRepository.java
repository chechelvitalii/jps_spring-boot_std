package com.vitaliy.hibernate.dao;

import com.vitaliy.hibernate.model.User;

import org.springframework.stereotype.Component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public List<User> finAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(userRoot);

        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
