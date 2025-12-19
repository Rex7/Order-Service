package com.example.order.model;

public class Order {

	   private Long id;
	  
	  private String orderUid;
	  
	
	  private String orderStatus;
	  
	  private String userId;
	  
	  private double amount;
	  
	  private String createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderUid() {
		return orderUid;
	}

	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
