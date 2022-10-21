package com.springboot.profiles.profilesDemo.entities;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Profile("default")
@Component       //This bean will run when profile is other dev and test
public class Tiger implements Animal{
    @Override
    public void makeSound() {
        System.out.println("Roar...");
    }
}
