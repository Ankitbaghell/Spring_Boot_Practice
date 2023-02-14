package com.flyway.demo.flywaydemo.controller;

import com.flyway.demo.flywaydemo.config.FlywaySlaveInitializer;
import com.flyway.demo.flywaydemo.entities.Datasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private FlywaySlaveInitializer flywaySlaveInitializer;

    @PostMapping("/api")
    public String runMigration(@RequestBody Datasource datasource){
        flywaySlaveInitializer.migrateFlyway(datasource.getUrl(), datasource.getUser(), datasource.getPassword(), datasource.getFlywayMigrationPath());
        return "migration done";
    }
}
