package com.pluralsight.fastpassui;

import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FastpassUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastpassUiApplication.class, args);
	}

	@Bean
	public WebClient.Builder loadBalancedWebClientBuilder(){
		return WebClient.builder();
	}

	@Bean
	public Supplier<FastPassToll> generateTollCharge(){
		return () -> new FastPassToll("800", "1001", 50.27f);
	}

}
