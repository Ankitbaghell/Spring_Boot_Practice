package com.unittestingdemo.unittestingdemo.service;

import com.unittestingdemo.unittestingdemo.entities.User;
import com.unittestingdemo.unittestingdemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);       // so that original DB is not connected
    }

    @Test
    void getALlUser() {
        userService.getALlUser();
        verify(userRepository).findAll();  // verifying that findAll method is called or not
    }

    @Test
    void getsingleUser() {
        when(userRepository.findById(2)).thenReturn(Optional.of(new User(2, "summit", "bhopal")));
        assertEquals("summit", userRepository.findById(2).get().getName());
    }

    @Test
    void createUser() {
        User user = new User(3, "summit", "bhopal");
        userService.createUser(user);
        verify(userRepository).save(user);
    }

    @Test
    void updateUser() {
        when(userRepository.findById(2)).thenReturn(Optional.of(new User(2, "summit", "bhopal")));
        User user = userService.getsingleUser(2);
        user.setName("Ram");
        userService.updateUser(user,2);
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser() {
        userService.deleteUser(2);
        verify(userRepository, times(1)).deleteById(2);

    }
}