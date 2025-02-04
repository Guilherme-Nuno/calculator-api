package com.guilherme.rest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "calculator-results", groupId = "calculator-group")
    public void receiveResult(String result) {
        System.out.println("Received calculation result: " + result);
    }
}