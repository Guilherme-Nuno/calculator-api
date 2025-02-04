package com.guilherme.rest;

import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RestConsumer {

    @Autowired
    CalculatorController calculatorController;

    @KafkaListener(topics = "calculator-results", groupId = "calculator-group")
    public void receiveResult(CalculatorResult result) {
        calculatorController.completeRequest(result);
    }
}