package com.guilherme.calculator;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final CalculatorService calculatorService;

    public KafkaConsumer(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculator-requests", groupId = "calculator-group")
    public void listenForCalculationRequests(String request) {
        // Call the service to process the calculation
        calculatorService.processCalculationRequest(request);
    }
}