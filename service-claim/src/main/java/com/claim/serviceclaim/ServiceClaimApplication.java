package com.claim.serviceclaim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceClaimApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceClaimApplication.class, args);
	}

}
