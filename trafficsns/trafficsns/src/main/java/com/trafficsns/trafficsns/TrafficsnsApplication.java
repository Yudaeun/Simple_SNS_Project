package com.trafficsns.trafficsns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TrafficsnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficsnsApplication.class, args);
	}

}
