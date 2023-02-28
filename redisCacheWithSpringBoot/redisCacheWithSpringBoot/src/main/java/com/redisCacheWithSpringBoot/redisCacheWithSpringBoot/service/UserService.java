package com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.service;

import com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.entity.User;
import com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;

        @Cacheable(value = "users", key = "#username")    //caching the values of user and username as key
        public User getUserByUsername(String username) {
            System.out.println("get from DB");
            User user = userRepository.findByUsername(username);
            return user;
        }

        @Cacheable(value = "users", key = "#id")   //caching the values of user and userId as key
        public User getUserById(Long id){
            System.out.println("get from DB");
            User user = userRepository.findById(id).get();
            return user;
        }

        @CachePut(value = "users", key = "#user.id")     //caching the newly created user
    public User saveUser(User user) {
        System.out.println("save from DB");
        return userRepository.save(user);
    }

    @CachePut(value = "users", key = "#user.id")       //caching the updates of old user
    public User updateUser(User user) {
        System.out.println("update from DB");
        User user1 = userRepository.findById(user.getId()).get();
        user1.setUsername(user.getUsername());
        user1.setAge(user.getAge());
        user1.setPassword(user.getPassword());
        return userRepository.save(user1);
    }

    @CacheEvict(value = "users", key = "#id")      //delete the cached value from cache for delete user
    public String deleteUser(Long id) {
        System.out.println("delete from DB");
        userRepository.deleteById(id);
        return "Deleted";
    }
}
