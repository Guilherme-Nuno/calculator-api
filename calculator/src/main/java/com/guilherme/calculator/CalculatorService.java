package com.guilherme.calculator;

import com.guilherme.common.CalculatorRequest;
import com.guilherme.common.CalculatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    @Lazy
    private CalculatorConsumer calculatorConsumer;

    @Autowired
    private CalculatorProducer calculatorProducer;

    public void processCalculationRequest(String id, CalculatorRequest request) {

        MDC.put("X-Request-ID", id);
        logger.info("Processing request");

        BigDecimal result;

        try {
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
        logger.info("Request processed successfully");

        } catch (ArithmeticException exception) {
            logger.error("Error processing request");
            throw new ArithmeticException("Division by 0 is not possible.");
        } finally {
            MDC.remove("X-Request-ID");
        }
    }
}