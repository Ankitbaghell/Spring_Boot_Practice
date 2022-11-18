package com.unittestingdemo.unittestingdemo.service;

import com.unittestingdemo.unittestingdemo.entities.User;
import com.unittestingdemo.unittestingdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getALlUser(){
        return userRepository.findAll();
    }

    public User getsingleUser(int id){
        return userRepository.findById(id).get();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user, int id){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
