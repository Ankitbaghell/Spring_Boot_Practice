package com.springboot.logging.loggingwithlog4j2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/api/{name}")
    public String greeting(@PathVariable String name) {
        log.debug("Request {}", name);
        if (name.equalsIgnoreCase("test")) {
            throw new RuntimeException("Opps Exception raised....");
        }
        String response = "Hi " + name ;
        log.debug("Response {}", response);
        return response;
    }
}
