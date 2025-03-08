package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mega_city_cab.model.Customer;

public class CustomerDAO {
	public Customer addCustomer (Customer customer ) {
		new UserDAO().addUser(customer);
		String query = "INSERT INTO Customer (userId) VALUES (?)"; //setting the foreign key
		
		try (Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) 
		{	
			statement.setInt(1, customer.getUserId()); 
			statement.executeUpdate();	
			
			//assigns and return the customer with the assigned userId
			ResultSet rs = statement.getGeneratedKeys();	
			if (rs.next()) {
				customer.setCustomerId(rs.getInt(1));
            }
			
			return customer; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Return null if insertion fails
	}
}
