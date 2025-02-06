package com.guilherme.common;

public class CalculatorError extends CalculatorResponse{
    private String error;

    public CalculatorError() {}

    public CalculatorError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
