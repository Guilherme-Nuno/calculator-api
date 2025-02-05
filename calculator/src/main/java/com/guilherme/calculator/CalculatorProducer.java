package com.guilherme.calculator;

import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CalculatorProducer {

    @Autowired
    KafkaTemplate<String, CalculatorResult> kafkaTemplate;

    public void sendCalculationResult(UUID id, CalculatorResult result) {
        kafkaTemplate.send(MessageBuilder
                    .withPayload(result)
                    .setHeader(KafkaHeaders.TOPIC, "calculator-results")
                    .setHeader("X-Request-ID", id)
                    .build()
            );
    }
}
