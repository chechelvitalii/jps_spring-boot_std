package com.vitaliy.hibernate.dao;

import com.vitaliy.hibernate.model.Address;
import com.vitaliy.hibernate.model.User;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryIT {

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
        Address user1 = userIterator.next();
        Address user2 = userIterator.next();
        foundUser.getAddresses().remove(user2);
        foundUser.getAddresses().add(createAddress(2,user));
        entityManager.flush();
//        //THEN
        User userResult = entityManager.find(User.class, 1);
        assertThat(userResult.getAddresses(), hasSize(1));
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