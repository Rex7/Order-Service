package com.example.order.repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.order.model.Order;

@Repository
public class OrderRepository {
	
	
	private final JdbcTemplate jdbcTemplate;
	
	public OrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	
	public long createOrder(Order order) {
		 String sql = """
			        INSERT INTO orders (
			            id, order_uuid, STATUS, CREATED_AT,USER_UUID,TOTAL_AMOUNT
			        ) VALUES (
			            orders_seq.NEXTVAL, ?, ?,?, ?,?
			        )
			        """;
		 Timestamp ts = Timestamp.valueOf(LocalDateTime.now());

			    KeyHolder keyHolder = new GeneratedKeyHolder();

			    jdbcTemplate.update(connection -> {
			        PreparedStatement ps =
			            connection.prepareStatement(sql, new String[] { "ID" });

			        ps.setString(1, order.getOrderUid());
			        ps.setString(2, order.getOrderStatus());
			        ps.setTimestamp(3, ts);
			        ps.setString(4, order.getUserId());
			        ps.setDouble(5, order.getAmount());

			        return ps;
			    }, keyHolder);

			    return keyHolder.getKey().longValue();
		
	}
	
	public String cancelOrder(String orderId) {
		String query="update orders set status = ? where ORDER_UUID = ?";
		int row=jdbcTemplate.update(query, "CANCELLED",orderId);
		if(row>0) {
			return "Updated";
		}
		return "Techinically issues";
	}
}
