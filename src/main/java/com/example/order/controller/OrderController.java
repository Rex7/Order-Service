package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
