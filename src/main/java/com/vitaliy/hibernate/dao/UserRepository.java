package com.vitaliy.hibernate.dao;

import com.vitaliy.hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class UserRepository {
    @Autowired
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }
}
