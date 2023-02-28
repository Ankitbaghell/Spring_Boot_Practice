package com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.repository;

import com.redisCacheWithSpringBoot.redisCacheWithSpringBoot.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable("users")
    User findByUsername(String username);
}
