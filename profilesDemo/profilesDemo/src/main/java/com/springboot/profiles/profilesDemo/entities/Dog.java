package com.springboot.profiles.profilesDemo.entities;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile("dev")    // This Bean is now only available in development phase
public class Dog implements Animal{
    @Override
    public void makeSound() {
        System.out.println("Bhow...");
    }
}
