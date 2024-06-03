package com.codedecode.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class OrderApplication {


	@Value("${User.microservice.baseUrl}")
	private String userMSBaseUrl;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}


	@Bean
	public WebClient getWebClient(){
		return WebClient.builder()
				.baseUrl("http://USER-SERVICE/user")
				.build();
	}


}
