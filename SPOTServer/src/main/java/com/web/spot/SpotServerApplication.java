package com.web.spot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = {"com.web.spot.mapper"})
@SpringBootApplication
public class SpotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotServerApplication.class, args);
	}

}
