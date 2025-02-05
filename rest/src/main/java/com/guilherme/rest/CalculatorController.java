package com.guilherme.rest;

import com.guilherme.common.CalculatorRequest;
import com.guilherme.common.CalculatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final ConcurrentMap<UUID, CompletableFuture<CalculatorResult>> pendingResults = new ConcurrentHashMap<>();

    @Autowired
    private RestProducer restProducer;

    @Autowired
    @Lazy
    private RestConsumer restConsumer;

    @GetMapping("/{operation}")
    public CompletableFuture<ResponseEntity<CalculatorResult>> sendRequest(
            @PathVariable String operation,
            @RequestParam BigDecimal a,
            @RequestParam BigDecimal b
            ) {
        UUID id = UUID.randomUUID();

        CalculatorRequest request = new CalculatorRequest(operation, a, b);

        CompletableFuture<CalculatorResult> futureResult = new CompletableFuture<>();
        pendingResults.put(id, futureResult);

        restProducer.sendCalculationRequest(id, request);

        return futureResult.thenApply(result ->
                ResponseEntity.ok()
                        .header("X-Request-ID", id.toString())
                        .body(result));
    }

    public void completeRequest(UUID id, CalculatorResult result) {
        CompletableFuture<CalculatorResult> futureResult = pendingResults.remove(id);
        if (futureResult != null) {
            futureResult.complete(result);
        }
    }
}
