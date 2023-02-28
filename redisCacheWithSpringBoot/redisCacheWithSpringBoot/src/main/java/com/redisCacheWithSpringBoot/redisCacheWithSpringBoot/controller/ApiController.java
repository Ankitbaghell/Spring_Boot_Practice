package com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.controller;

import com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.entity.User;
import com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

        @Autowired
        private UserService userService;

        @GetMapping("/users/username/{username}")
        public User getUserByUsername(@PathVariable String username) {
            return userService.getUserByUsername(username);
        }

        @GetMapping("/users/{id}")
        public User getUserById(@PathVariable Long id) {
                return userService.getUserById(id);
        }

        @PostMapping("/users/create")
        public User createUser(@RequestBody User user) {
                return userService.saveUser(user);
        }

        @PutMapping("/users/update")
        public User updateUser(@RequestBody User user) {
                return userService.updateUser(user);
        }

        @DeleteMapping("/users/delete/{id}")
        public String deleteUser(@PathVariable Long id) {
                return userService.deleteUser(id);
        }
}
