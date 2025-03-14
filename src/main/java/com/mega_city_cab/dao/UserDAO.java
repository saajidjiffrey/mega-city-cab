package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mega_city_cab.model.User;

public class UserDAO {
	public void addUser (User user) throws Exception {
		String query = "INSERT INTO User (name, address,  NIC, phone, email, userName, password, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try  
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					
			statement.setString(1, user.getName()); 
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getNIC());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getUserName());
			statement.setString(7, user.getPassword());
			statement.setString(8, user.getRole());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating a user failed, no rows affected.");
	        }
			
			// below implementation retrieves auto generated primary key(userId) for user, after inserting a new user
			ResultSet rs = statement.getGeneratedKeys();		//fetches the generated key from database
			if (rs.next()) {		//checks if there is a generated key
                user.setUserId(rs.getInt(1));		//retrieves the first column which is (userId) and set it to the USER object to use it later.
            } else {
	            throw new SQLException("Creating a user failed, no ID obtained.");
	        }
			
		} catch (SQLException e) {
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public User getUserByUserName (String userName) throws Exception {
		String query = "SELECT * FROM User WHERE userName = ?	";
		
		try 
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new User (
							rs.getInt("userId"),
							rs.getString("name"),
							rs.getString("address"),
							rs.getString("NIC"),
							rs.getString("phone"),
							rs.getString("email"),
							rs.getString("userName"),
							rs.getString("password"),
							rs.getString("role")
						);
			}
		} catch (SQLException e) {
			throw new Exception("Database error: " + e.getMessage(), e);
		}
		return null;
	}
}
