package com.mapstructwithbuilder.mapstructwithbuilder.controller;

import com.mapstructwithbuilder.mapstructwithbuilder.mapper.UserMapper;
import com.mapstructwithbuilder.mapstructwithbuilder.model.User;
import com.mapstructwithbuilder.mapstructwithbuilder.request.UserRequest;
import com.mapstructwithbuilder.mapstructwithbuilder.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

//    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

     @PostMapping("/user")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
         User user = userMapper.userRequestToUser(userRequest).build();
         System.out.println("converted to user object");

         UserResponse userResponse = userMapper.userToUserResponse(user);
         System.out.println("converted to userResponse object  :-  " + userResponse);
         return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
     }
}
