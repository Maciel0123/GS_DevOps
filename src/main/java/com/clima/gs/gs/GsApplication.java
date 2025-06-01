package com.clima.gs.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GsApplication {

	public static void main(String[] args) {

		System.setProperty("DB_URL", System.getenv("DB_URL"));
		System.setProperty("DB_USER", System.getenv("DB_USER"));
		System.setProperty("DB_PASSWORD", System.getenv("DB_PASSWORD"));

		SpringApplication.run(GsApplication.class, args);
	}
}
