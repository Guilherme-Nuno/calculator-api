package com.guilherme.rest;

import com.guilherme.common.CalculatorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private RestProducer restProducer;

    @Autowired
    private RestConsumer restConsumer;

    @GetMapping("/{operation}")
    public void sendRequest(
            @PathVariable String operation,
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b
            ) {
        CalculatorRequest request = new CalculatorRequest(operation, a, b);

        restProducer.sendCalculationRequest(request);
    }
}
