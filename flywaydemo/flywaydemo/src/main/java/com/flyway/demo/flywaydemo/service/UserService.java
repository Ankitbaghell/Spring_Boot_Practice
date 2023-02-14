package com.flyway.demo.flywaydemo.service;

import com.flyway.demo.flywaydemo.config.FlywaySlaveInitializer;
import com.flyway.demo.flywaydemo.entities.User;
import com.flyway.demo.flywaydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private FlywaySlaveInitializer flywaySlaveInitializer;

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user){
        var datasource_url = "jdbc:mysql://localhost:3306/flywaydemo3";
        var datasource_username = "root";
        var datasource_password = "mysql";
        var datasource_flywayMigrationPath = "db.migration2";

        boolean success = flywaySlaveInitializer.migrateFlyway(datasource_url, datasource_username, datasource_password, datasource_flywayMigrationPath);
        if(success){
            userRepository.createUser(user);
            return "user created";
        }
        else
            return "Error in created user";
    }
}
