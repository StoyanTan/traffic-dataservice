package com.kaggle.trafficdata.trafficdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TrafficDataserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficDataserviceApplication.class, args);
	}

}
