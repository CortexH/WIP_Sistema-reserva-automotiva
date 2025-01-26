package com.example.SistemaReservaAutomotiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SistemaReservaAutomotivaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaReservaAutomotivaApplication.class, args);
	}
}
