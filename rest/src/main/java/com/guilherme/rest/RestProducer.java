package com.guilherme.rest;

import com.guilherme.common.CalculatorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class RestProducer {

    @Autowired
    private KafkaTemplate<String, CalculatorRequest> kafkaTemplate;

    public void sendCalculationRequest(CalculatorRequest request) {
        kafkaTemplate.send("calculator-requests", request);
    }
}
