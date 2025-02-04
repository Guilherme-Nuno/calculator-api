package com.guilherme.calculator;

import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CalculatorProducer {

    @Autowired
    KafkaTemplate<String, CalculatorResult> kafkaTemplate;

    public void sendCalculationResult(CalculatorResult result) {
        kafkaTemplate.send("calculator-results", result);
    }
}
