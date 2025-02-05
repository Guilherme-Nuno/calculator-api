package com.guilherme.rest;

import com.guilherme.common.CalculatorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

@Component
public class RestProducer {

    @Autowired
    private KafkaTemplate<String, CalculatorRequest> kafkaTemplate;

    public void sendCalculationRequest(String id, CalculatorRequest request) {
        kafkaTemplate.send(MessageBuilder
                            .withPayload(request)
                            .setHeader(KafkaHeaders.TOPIC, "calculator-requests")
                            .setHeader("X-Request-ID", id)
                            .build()
        );
    }
}
