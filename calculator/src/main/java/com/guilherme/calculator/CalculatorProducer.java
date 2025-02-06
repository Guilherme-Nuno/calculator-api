package com.guilherme.calculator;

import com.guilherme.common.CalculatorError;
import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CalculatorProducer {

    @Autowired
    KafkaTemplate<String, CalculatorResult> kafkaTemplate;

    public void sendCalculationResult(String id, CalculatorResult result) {
        kafkaTemplate.send(MessageBuilder
                    .withPayload(result)
                    .setHeader(KafkaHeaders.TOPIC, "calculator-results")
                    .setHeader("X-Request-ID", id)
                    .build()
            );
    }

    public void sendCalculationError(String id, CalculatorError error) {
        kafkaTemplate.send(MessageBuilder
                .withPayload(error)
                .setHeader(KafkaHeaders.TOPIC, "calculator-errors")
                .setHeader("X-Request-ID", id)
                .build()
        );
    }
}
