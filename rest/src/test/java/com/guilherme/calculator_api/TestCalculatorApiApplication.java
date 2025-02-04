package com.guilherme.calculator_api;

import org.springframework.boot.SpringApplication;

public class TestCalculatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(CalculatorApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
