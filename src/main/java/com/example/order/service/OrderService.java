package com.example.order.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

	@Autowired
	WebClient.Builder builder;

	@Autowired
	OrderRepository orderRepository;
	
	public Mono<String> processorder(String orderId) {
		Mono<String> response=null;
		 WebClient webClient = builder
	                .baseUrl("http://payment-service") // Eureka resolves this
	                .build();
		PayMentRequest payRequest=new PayMentRequest();
		payRequest.setPayId("11");
		
	response = webClient.post()
				.uri("/payment/pay")
				.bodyValue(payRequest)
				.retrieve()
				.bodyToMono(String.class);
		return  response;

	}
	
	public Long createOrder(String userId) {
		String orderUuid = UUID.randomUUID().toString();
		String userId_UUID=UUID.randomUUID().toString();
		Order order=new Order();
		order.setUserId(userId_UUID);
		order.setOrderStatus("Cretated");
		order.setOrderUid(orderUuid);
		order.setAmount(100.50);
		LocalDateTime createdDate = LocalDateTime.now();
		order.setCreatedAt(createdDate.toString());
		return orderRepository.createOrder(order);
	}
	
	public String cancelOrder(String orderId) {
		return orderRepository.cancelOrder(orderId);
	}

}
