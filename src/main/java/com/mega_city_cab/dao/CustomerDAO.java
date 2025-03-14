package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Customer;

public class CustomerDAO {
	public Customer addCustomer (Customer customer ) throws Exception{
		new UserDAO().addUser(customer);
		String query = "INSERT INTO Customer (userId) VALUES (?)"; //setting the foreign key
		
		try  
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, customer.getUserId()); 
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating customer failed, no rows affected.");
	        }
			
			//assigns and return the customer with the assigned userId
			ResultSet rs = statement.getGeneratedKeys();	
			if (rs.next()) {
	            customer.setCustomerId(rs.getInt(1));
	        } else {
	            throw new SQLException("Creating customer failed, no ID obtained.");
	        }
			
			return customer; 
		} catch (SQLException e) {
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public Customer getCustomerByUserId(int userId) throws SQLException {
		String query = "SELECT u.userId, u.name, u.address, u.NIC, u.phone, u.email, u.userName, u.password, u.role, " +
                "c.customerId " +
                "FROM User u " +
                "JOIN Customer c ON u.userId = c.userId " +
                "WHERE u.userId = ?";
		Customer customer = null;
        
        try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
        	statement.setInt(1,userId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("userId"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("NIC"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getInt("customerId")
                    );
                } else {
                throw new SQLException("No customer found with userId: " + userId);
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving customer: " + e.getMessage(), e);
		}
        
        return customer;
    }
}
