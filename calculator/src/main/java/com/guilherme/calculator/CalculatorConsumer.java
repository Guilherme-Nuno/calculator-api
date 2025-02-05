package com.guilherme.calculator;

import com.guilherme.common.CalculatorRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CalculatorConsumer {

    private final CalculatorService calculatorService;

    public CalculatorConsumer(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculator-requests", groupId = "calculator-group")
    public void listenForCalculationRequests(@Header("X-Request-ID") UUID id, CalculatorRequest request) {
        calculatorService.processCalculationRequest(id, request);
    }
}