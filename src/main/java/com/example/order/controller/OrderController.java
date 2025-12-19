package com.example.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.model.Order;
import com.example.order.service.OrderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	
	@PostMapping("/processOrder")
	public Mono<String>  processOrder() {
		System.out.print("reched here0");
		return orderService.processorder("11");
	}
	
	@PostMapping("/createOrder")
	public Mono<Map<String,Object>> createOrder(@RequestBody String userId){

	Long recprdUpdated	=orderService.createOrder(userId);
		Map<String,Object> responseMap =new HashMap<>();
		responseMap.put("orderId","1");
		responseMap.put("orderStatus","creatd");
		responseMap.put("recordupdated", recprdUpdated);
		return Mono.just(responseMap);  
	}
	
	@PostMapping("/cancelOrder")
	public Mono<Map<String,Object>> cancelOrder(@RequestBody Order order){
		System.out.println("cancelled order called"+order.getOrderUid());
		String response=orderService.cancelOrder(order.getOrderUid());
		Map<String,Object> responseMap =new HashMap<>();
		responseMap.put("orderId",order.getOrderUid());
		responseMap.put("orderStatus","cancelled");
		responseMap.put("ResponseMsg", response);
		return Mono.just(responseMap);  
	}
	
	

}
