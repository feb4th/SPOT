package com.ssafy.spot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.ssafy.spot.repository")
@EnableConfigurationProperties
@SpringBootApplication
public class SpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotApplication.class, args);
	}

}
