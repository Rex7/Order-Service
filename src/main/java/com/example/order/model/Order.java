package com.example.order.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order {

	   private Long id;
	  
	  private String orderUid;
	  
	
	  private String orderStatus;
	  
	  private String userId;
	  
	  private BigDecimal amount;
	  
	  private String createdAt;

	

}
