package com.guilherme.rest;

import com.guilherme.common.CalculatorError;
import com.guilherme.common.CalculatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class RestConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RestConsumer.class);

    @Autowired
    CalculatorController calculatorController;

    @KafkaListener(topics = "calculator-results", groupId = "calculator-group")
    public void receiveResult(@Header("X-Request-ID") String id, CalculatorResult message) {
        MDC.put("X-Request-ID", id);
        logger.info("Received result from calculator service");
        MDC.remove("X-Request-ID");

        calculatorController.completeRequest(id, (CalculatorResult) message);
        }

    @KafkaListener(topics = "calculator-errors", groupId = "calculator-group")
    public void receiveError (@Header("X-Request-ID") String id, CalculatorError message) {
        MDC.put("X-Request-ID", id);
        logger.info("Received error from calculator service");
        MDC.remove("X-Request-ID");

        calculatorController.completeRequestWithError(id, message);
    }
}