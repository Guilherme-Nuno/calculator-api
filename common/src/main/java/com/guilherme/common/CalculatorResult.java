package com.guilherme.common;

import java.math.BigDecimal;
import java.util.UUID;

public class CalculatorResult {
    private UUID id;
    private BigDecimal result;

    public CalculatorResult() {}

    public CalculatorResult(UUID id, BigDecimal result) {
        this.id = id;
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public UUID getId() {
        return id;
    }
}
