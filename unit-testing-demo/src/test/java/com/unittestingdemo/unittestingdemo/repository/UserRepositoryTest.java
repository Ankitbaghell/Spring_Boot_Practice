package com.unittestingdemo.unittestingdemo.repository;

import com.unittestingdemo.unittestingdemo.entities.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    //create tesing
    @Test
    @Order(1)
    public void createUserTest(){
        User user = new User(1, "ANkit", "Vidisha");
        userRepository.save(user);
        assertNotNull(userRepository.findById(1).get());
    }

    //get All testing
    @Test
    @Order(2)
    public void getAllUserTest(){
        List<User> users = userRepository.findAll();
        assertEquals(1,users.size());
    }

    //get single user testing
    @Test
    @Order(3)
    public void getSingleUserTest(){
        User user = userRepository.findById(1).get();
        assertEquals("Vidisha", user.getCity());
    }

    // update user testing
    @Test
    @Order(4)
    public  void updateUserTest(){
        User user = userRepository.findById(1).get();
        user.setName("Virat");
        User save = userRepository.save(user);
        assertEquals("Virat", save.getName());
    }

    //delete user testing
    @Test
    @Order(5)
    public void deleteUserTest(){
        userRepository.deleteById(1);
        assertThat(userRepository.existsById(1)).isFalse();
    }

}