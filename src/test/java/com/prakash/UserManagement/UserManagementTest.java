package com.prakash.UserManagement;

import com.prakash.UserManagement.user.UserRepository;
import com.prakash.UserManagement.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserManagementTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew(){
        User user=new User();

        user.setEmail("pr78847@gmail.com");
        user.setPassword("prakash1213");
        user.setFirstName("Prakash");
        user.setLastName("Arjunan");
        User saveUser = userRepository.save(user);
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
        Assertions.assertThat(saveUser.getEmail()).matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }

    @Test
    public void testAllUser(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        int userId=3;
        Optional<User> userById = userRepository.findById(userId);
        User user = userById.get();
        user.setLastName("Arjunan");
       userRepository.save(user);
        User updatedUser =  userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getLastName()).isEqualTo("Arjunan");

    }

    @Test
    public void testGetUser(){
        int userId=2;
        Optional<User> byId = userRepository.findById(userId);
        Assertions.assertThat(byId).isPresent();
        System.out.println(byId.get());
    }

    @Test
    public void testDeleteUser(){
        int userId=3;
        userRepository.deleteById(userId);
        Optional deleteUser=userRepository.findById(userId);
        Assertions.assertThat(deleteUser).isNotPresent();

    }
}
