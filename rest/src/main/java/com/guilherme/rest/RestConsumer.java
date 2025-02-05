package com.guilherme.rest;

import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RestConsumer {

    @Autowired
    CalculatorController calculatorController;

    @KafkaListener(topics = "calculator-results", groupId = "calculator-group")
    public void receiveResult(@Header("X-Request-ID") UUID id, CalculatorResult result) {
        calculatorController.completeRequest(id, result);
    }
}