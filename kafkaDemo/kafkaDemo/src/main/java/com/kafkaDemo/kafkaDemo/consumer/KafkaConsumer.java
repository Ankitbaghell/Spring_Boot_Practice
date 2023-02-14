package com.kafkaDemo.kafkaDemo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafkademo", groupId = "myGroup")
    public void consume(String message){
        System.out.println("Message received : " + message);
    }
}
