package com.guilherme.calculator;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CalculatorService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processCalculationRequest(String request) {
        // Parse the request message to get operands and operation (for simplicity)
        String[] parts = request.split(" ");
        String operation = parts[0];  // Example: "sum"
        BigDecimal operand1 = new BigDecimal(parts[1]);
        BigDecimal operand2 = new BigDecimal(parts[2]);

        BigDecimal result = BigDecimal.ZERO;
        switch (operation) {
            case "sum":
                result = operand1.add(operand2);
                break;
            case "subtract":
                result = operand1.subtract(operand2);
                break;
            case "multiply":
                result = operand1.multiply(operand2);
                break;
            case "divide":
                result = operand1.divide(operand2, RoundingMode.HALF_UP);
                break;
        }

        // Send the result back to the 'calculator-results' topic
        kafkaTemplate.send("calculator-results", result.toString());
    }
}