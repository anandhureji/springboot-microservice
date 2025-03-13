package com.student.student_service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentServiceApplication {

	/*** end point of external api ***/
	@Value("${address.service.url}")
	public String addressServiceUrl;


	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}



	/** create WebClient to call external api ***/
	@Bean
	public WebClient webClient(){

		WebClient webClient = WebClient.builder()
				.baseUrl(addressServiceUrl)
				.build();
		return webClient;

	}

}
