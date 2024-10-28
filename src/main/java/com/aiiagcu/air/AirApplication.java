package com.aiiagcu.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AirApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirApplication.class, args);
	}

}
