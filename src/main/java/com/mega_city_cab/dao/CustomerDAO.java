package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mega_city_cab.model.Customer;

public class CustomerDAO {
	public void addCustomer (Customer customer ) {
		new UserDAO().addUser(customer);
		String query = "INSERT INTO Customer (userId) VALUES (?)"; //setting the foreign key
		
		try {
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, customer.getUserId()); 
			statement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
