package com.ssafy.spot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@MapperScan(basePackages = {"com.ssafy.spot.mapper"})
@EnableMongoRepositories(basePackages = {"com.ssafy.spot.repository"})
@EnableConfigurationProperties
@SpringBootApplication
public class SpotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotServerApplication.class, args);
	}

}
