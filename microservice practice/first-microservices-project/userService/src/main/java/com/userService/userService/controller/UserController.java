package com.userService.userService.controller;

import com.userService.userService.entities.User;
import com.userService.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    //get user
    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId){
        User user = userService.getUser(userId);
        // establishing communication between userService and ContactService
        List contacts = restTemplate.getForObject("http://contact-service/contact/"+userId, List.class);
        user.setContacts(contacts);
        return user;
    }

}
