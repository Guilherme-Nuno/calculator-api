package com.guilherme.common;

import java.math.BigDecimal;

public class CalculatorResult extends CalculatorResponse{
    private BigDecimal result;

    public CalculatorResult() {}

    public CalculatorResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }
}
