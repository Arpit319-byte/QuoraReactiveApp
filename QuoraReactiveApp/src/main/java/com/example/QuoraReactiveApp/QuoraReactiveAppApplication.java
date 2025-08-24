package com.example.QuoraReactiveApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class QuoraReactiveAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoraReactiveAppApplication.class, args);
	}

}
