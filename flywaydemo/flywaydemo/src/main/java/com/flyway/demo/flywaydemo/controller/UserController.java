package com.flyway.demo.flywaydemo.controller;

import com.flyway.demo.flywaydemo.entities.User;
import com.flyway.demo.flywaydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public String createUser(@RequestBody User user){
        String result = userService.createUser(user);
        return result;
    }

}
