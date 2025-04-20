package com.example.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

	@Autowired
	WebClient.Builder builder;

	public Mono<String> processorder(String orderId) {
		Mono<String> response=null;
		 WebClient webClient = builder
	                .baseUrl("http://payment-service") // Eureka resolves this
	                .build();
		 
		PayMentRequest payRequest=new PayMentRequest();
		payRequest.setPayId("11");
		
	response	= webClient.post()
				.uri("/payment/pay")
				.bodyValue(payRequest)
				.retrieve()
				.bodyToMono(String.class);
		return  response;

	}

}
