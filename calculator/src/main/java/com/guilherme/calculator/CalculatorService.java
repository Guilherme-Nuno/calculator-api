package com.guilherme.calculator;

import com.guilherme.common.CalculatorRequest;
import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {

    @Autowired
    @Lazy
    private CalculatorConsumer calculatorConsumer;

    @Autowired
    private CalculatorProducer calculatorProducer;

    public void processCalculationRequest(CalculatorRequest request) {
        BigDecimal result = BigDecimal.ZERO;

        switch (request.getOperation()) {
            case "add":
                result = request.getA().add(request.getB());
                break;
            case "sub":
                result = request.getA().subtract(request.getB());
                break;
            case "mul":
                result = request.getA().multiply(request.getB());
                break;
            case "div":
                result = request.getA().divide(request.getB(), RoundingMode.HALF_UP);
        }

        calculatorProducer.sendCalculationResult(new CalculatorResult(request.getId(), result));
    }
}