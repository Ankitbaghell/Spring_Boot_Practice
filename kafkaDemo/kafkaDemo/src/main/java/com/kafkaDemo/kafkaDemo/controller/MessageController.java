package com.kafkaDemo.kafkaDemo.controller;

import com.kafkaDemo.kafkaDemo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private KafkaProducer kafkaProducer;

    //http://localhost:8080/send?message=.....
    @GetMapping("/send")
    public String sendMsg(@RequestParam("message") String message){
        kafkaProducer.sendMessage(message);
        return "message sent successfully";
    }
}

