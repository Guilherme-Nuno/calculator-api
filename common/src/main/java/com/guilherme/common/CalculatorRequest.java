package com.guilherme.common;

import java.math.BigDecimal;
import java.util.UUID;

public class CalculatorRequest {
    private UUID id;
    private String operation;
    private BigDecimal a;
    private BigDecimal b;

    // Empty constructor for serialization
    public CalculatorRequest() {}

    public CalculatorRequest(UUID id, String operation, BigDecimal a, BigDecimal b) {
        this.id = id;
        this.operation = operation;
        this.a = a;
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public BigDecimal getA() {
        return a;
    }

    public BigDecimal getB() {
        return b;
    }

    public UUID getId() {
        return id;
    }
}
