package com.guilherme.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, CalculatorRequest> kafkaTemplate;

    public void sendCalculationRequest(String topic, CalculatorRequest request) {
        kafkaTemplate.send(topic, request);
    }
}
