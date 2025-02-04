package com.guilherme.rest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RestConsumer {

    @KafkaListener(topics = "calculator-results", groupId = "calculator-group")
    public void receiveResult(String result) {
        System.out.println("Received calculation result: " + result);
    }
}