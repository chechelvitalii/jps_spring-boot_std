package com.vitaliy.hibernate.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.vitaliy.hibernate.HibernateApplication;
import com.vitaliy.hibernate.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateApplication.class) //TODO resolve how to set up test config here ?
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})

public class UserRepositoryWithDbUnotIT {

    @Autowired
    private UserRepository userRepository;

    //TODO in future @ExpectedDatabase("expectedData.xml")
    @Test
    /**type = DatabaseOperation.CLEAN_INSERT default for DatabaseSetup*/
    @DatabaseSetup("/userData.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void saveUser() throws Exception {
        //GIVEN
        //WHEN
        List<User> allUsers = userRepository.finAll();
        //THEN
        assertThat(allUsers, hasSize(3));
        allUsers.stream().forEach(user -> assertFalse(user.getAddresses().isEmpty()));
    }

}