package com.vitaliy.hibernate.dao;

import com.vitaliy.hibernate.model.Address;
import com.vitaliy.hibernate.model.User;
import org.assertj.core.util.Sets;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryIT  extends DBTestCase { //TODO http://devcolibri.com/3575

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveUser() throws Exception {
        //GIVEN
        User user = createUserWithTwoAddress();
        //WHEN
        entityManager.persist(user);
        User foundUser = entityManager.find(User.class, 1);
        assertThat(foundUser.getAddresses(), hasSize(2));

        Iterator<Address> userIterator = foundUser.getAddresses().iterator();
        Address address1 = userIterator.next();
        assertThat(address1.getId(), is(1));
        Address address2 = userIterator.next();
        assertThat(address2.getId(), is(2));

        Address newAddress = createAddress(2, foundUser);

        foundUser.removeAddress(address2);
        entityManager.flush();
        foundUser.addAddress(newAddress);
        entityManager.flush();
//        //THEN
        User userResult = entityManager.find(User.class, 1);
        assertThat(userResult.getAddresses(), hasSize(2));
        System.out.println(userResult);
    }

    private User createUserWithTwoAddress() {
        User user = new User();
        user.setId(1);
        user.setName("Vasiliy");
        user.setSurname("Pupkin");
        Set<Address> addresses = Sets.newLinkedHashSet(
                createAddress(1, user),
                createAddress(2, user));
        user.setAddresses(addresses);
        return user;
    }

    private Address createAddress(int index, User user) {
        return Address.builder()
                .id(index)
                .buildNumber(index+"a")
                .street("Baker Street")
                .user(user)
                .build();
    }

}