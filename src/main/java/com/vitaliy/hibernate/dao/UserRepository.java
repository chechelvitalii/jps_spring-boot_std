package com.vitaliy.hibernate.dao;

import com.vitaliy.hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserRepository {
    @Autowired
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public List<User> finAll() {
        return entityManager
                .createNativeQuery("Select * From user ")
                .getResultList();
    }
}
