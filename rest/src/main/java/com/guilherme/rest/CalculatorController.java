package com.guilherme.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @GetMapping("/{operation}")
    public void sendRequest(
            @PathVariable String operation,
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b
            ) {
        CalculatorRequest request = new CalculatorRequest(operation, a, b);

        kafkaProducer.sendCalculationRequest("calculator-requests", request);
    }
}
