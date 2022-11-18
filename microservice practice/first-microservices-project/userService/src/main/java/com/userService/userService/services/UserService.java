package com.userService.userService.services;

import com.userService.userService.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //Dummy Data
    List<User> list = List.of(new User(1, "Ankit", "9876543210"),
            new User(2, "sumit", "5463782"),
            new User(3, "Virat", "563829543"),
            new User(4, "Ramesh", "1356890526"));


    public User getUser(int userId){
        return list.stream().filter(user -> user.getId()==userId).findAny().orElse(null);
    }
}
