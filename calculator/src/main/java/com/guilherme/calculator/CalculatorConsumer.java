package com.guilherme.calculator;

import com.guilherme.common.CalculatorRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorConsumer {

    private final CalculatorService calculatorService;

    public CalculatorConsumer(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculator-requests", groupId = "calculator-group")
    public void listenForCalculationRequests(CalculatorRequest request) {
        calculatorService.processCalculationRequest(request);
    }
}