package com.springboot.profiles.profilesDemo.entities;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile("test")   // This Bean is now only available in test phase
public class Cat implements Animal{
    @Override
    public void makeSound() {
        System.out.println("Mheow...");
    }
}
