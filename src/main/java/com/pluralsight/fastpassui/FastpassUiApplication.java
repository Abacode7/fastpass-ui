package com.pluralsight.fastpassui;

import java.util.ArrayList;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class FastpassUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastpassUiApplication.class, args);
	}

	@Bean
	public WebClient.Builder loadBalancedWebClientBuilder(){
		return WebClient.builder();
	}

	//@Bean
	public Supplier<FastPassToll> generateTollChargeSlow(){
		return () -> new FastPassToll("800", "1001", 50.27f);
	}

	@Bean
	public Supplier<Flux<Message<FastPassToll>>> generateThreeCharges(){
		ArrayList<Message<FastPassToll>> tolls = new ArrayList<>();
		tolls.add(MessageBuilder.withPayload(new FastPassToll("800", "1001", 50.27f)).setHeader("speed", "slow").build());
		tolls.add(MessageBuilder.withPayload(new FastPassToll("801", "1002", 40.32f)).setHeader("speed", "fast").build());
		tolls.add(MessageBuilder.withPayload(new FastPassToll("802", "1003", 70.12f)).setHeader("speed", "slow").build());
		return () -> Flux.fromIterable(tolls);
	}

}
