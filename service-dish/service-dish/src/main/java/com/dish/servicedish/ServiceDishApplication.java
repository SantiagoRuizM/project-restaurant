package com.dish.servicedish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDishApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDishApplication.class, args);
	}

}
