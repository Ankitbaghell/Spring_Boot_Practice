package com.unittestingdemo.unittestingdemo.repository;

import com.unittestingdemo.unittestingdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
