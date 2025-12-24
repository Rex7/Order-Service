package com.example.order.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDTO {

	private List<Integer> productList;
	private BigDecimal amount;
	private String userId;

}
