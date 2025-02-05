package com.guilherme.calculator;

import com.guilherme.common.CalculatorRequest;
import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class CalculatorService {

    @Autowired
    @Lazy
    private CalculatorConsumer calculatorConsumer;

    @Autowired
    private CalculatorProducer calculatorProducer;

    public void processCalculationRequest(UUID id, CalculatorRequest request) {
        BigDecimal result;

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
                break;
            default:
                result = null;
        }

        calculatorProducer.sendCalculationResult(id, new CalculatorResult(result));
    }
}