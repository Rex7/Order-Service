package com.example.order.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/db")
public class DBCheckController {
	
	@Autowired
	DataSource dataSource;
	
	@PostMapping("/db_check")
	public String dbCheck() throws SQLException {
		try (Connection c = dataSource.getConnection()) {
            return "Oracle Connected: " + c.getMetaData().getDatabaseProductVersion();
        }
	}

}
