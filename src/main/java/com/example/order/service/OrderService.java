package com.example.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order.DTO.OrderRequestDTO;
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
	
	public ResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
		String orderUuid = UUID.randomUUID().toString();
		String userId_UUID=UUID.randomUUID().toString();
		ResponseDTO responseDTO=new ResponseDTO();
		List<Integer> productList=orderRequestDTO.getProductList();
		Order order=new Order();
		order.setUserId(orderRequestDTO.getUserId());
		order.setOrderStatus("created");
		order.setOrderUid(orderUuid);
		order.setAmount(orderRequestDTO.getAmount());
		LocalDateTime createdDate = LocalDateTime.now();
		order.setCreatedAt(createdDate.toString());
		Long updatedRecords=orderRepository.createOrder(order);
		if(updatedRecords>0) {
			Long orderInsert=orderRepository.itemInsert(productList,orderUuid);
		}
		responseDTO.setUserId_UUID(userId_UUID);
		responseDTO.setUserorderUuid(orderUuid);
		return  responseDTO;
	}
	
	public String cancelOrder(String orderId) {
		return orderRepository.cancelOrder(orderId);
	}

}
