package com.guilherme.rest;

import com.guilherme.common.CalculatorError;
import com.guilherme.common.CalculatorRequest;
import com.guilherme.common.CalculatorResponse;
import com.guilherme.common.CalculatorResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private final ConcurrentMap<String, CompletableFuture<CalculatorResponse>> pendingResults = new ConcurrentHashMap<>();

    @Autowired
    private RestProducer restProducer;

    @Autowired
    @Lazy
    private RestConsumer restConsumer;

    @GetMapping("/{operation}")
    public CompletableFuture<ResponseEntity<CalculatorResponse>> sendRequest(
            @PathVariable String operation,
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b
            ) {
        String id = MDC.get("X-Request-ID");
        logger.info("Received request for {}: a = {}, b = {}", operation, a, b);

        CalculatorRequest request = new CalculatorRequest(operation, a, b);

        CompletableFuture<CalculatorResponse> futureResult = new CompletableFuture<>();
        pendingResults.put(id, futureResult);

        restProducer.sendCalculationRequest(id, request);
        logger.info("Sent request for calculator service.");

        return futureResult
                .thenApply(result ->
                        ResponseEntity.ok()
                                .header("X-Request-ID", id)
                                .body(result)
                )
                .exceptionally(ex ->
                        ResponseEntity.badRequest()
                                .header("X-Request-ID", id)
                                .body(new CalculatorError(ex.getMessage()))
                );
    }

    public void completeRequest(String id, CalculatorResult result) {
        CompletableFuture<CalculatorResponse> futureResult = pendingResults.remove(id);
        if (futureResult != null) {
            futureResult.complete(result);
            MDC.put("X-Request-ID", id);
            logger.info("Completed request");
            MDC.remove("X-Request-ID");
        }
    }

    public void completeRequestWithError(String id, CalculatorError message) {
        CompletableFuture<CalculatorResponse> futureResult = pendingResults.remove(id);
        if (futureResult != null) {
            futureResult.completeExceptionally(new RuntimeException(message.getError()));
            MDC.put("X-Request-ID", id);
            logger.info("Request completed with error: {}", message.getError());
            MDC.remove("X-Request-ID");
        }
    }
}
