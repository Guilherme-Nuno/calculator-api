package com.guilherme.common;

import org.springframework.boot.SpringApplication;

public class TestCalculatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(CalculatorApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
