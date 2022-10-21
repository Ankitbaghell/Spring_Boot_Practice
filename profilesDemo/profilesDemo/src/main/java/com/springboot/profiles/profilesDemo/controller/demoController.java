package com.springboot.profiles.profilesDemo.controller;

import com.springboot.profiles.profilesDemo.entities.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @Autowired
//    @Qualifier("dog")
    private Animal animal;

    @GetMapping("/animal")
    public void sound(){
        animal.makeSound();
    }
}
