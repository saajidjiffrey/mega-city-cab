package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Driver;

public class DriverDAO {
	public Driver addDriver (Driver driver) throws Exception {
		new UserDAO().addUser(driver);
		String query = "INSERT INTO Driver (userId, licenseNo) VALUES (?, ?)"; //setting the foreign key and the license no
		
		try 
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, driver.getUserId()); 
			statement.setString(2, driver.getLicenseNo()); 
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating diver failed, no rows affected.");
	        }
			
			ResultSet rs = statement.getGeneratedKeys();	
			if (rs.next()) {
				driver.setDriverId(rs.getInt(1));
            } else {
	            throw new SQLException("Creating driver failed, no ID obtained.");
	        }
			
			return driver;
		} catch (SQLException e) {
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public Driver getDriverByUserId(int userId) throws Exception {
		String query = "SELECT u.userId, u.name, u.address, u.NIC, u.phone, u.email, u.userName, u.password, u.role, " +
                "d.driverId, d.licenseNo " +
                "FROM User u " +
                "JOIN Driver d ON u.userId = d.userId " +
                "WHERE u.userId = ?";
		Driver driver = null;
        
        try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
        	statement.setInt(1,userId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	driver = new Driver(
                        resultSet.getInt("userId"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("NIC"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getInt("driverId"),
                        resultSet.getString("licenseNo")
                    );
                } else {
                throw new Exception("No driver found with userId: " + userId);
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving driver: " + e.getMessage(), e);
		}
        
        return driver;
    }
}
