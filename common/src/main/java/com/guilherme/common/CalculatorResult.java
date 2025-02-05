package com.guilherme.common;

import java.math.BigDecimal;
import java.util.UUID;

public class CalculatorResult {
    private BigDecimal result;

    public CalculatorResult() {}

    public CalculatorResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }
}
