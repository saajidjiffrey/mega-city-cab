package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mega_city_cab.model.Driver;

public class DriverDAO {
	public Driver addDriver (Driver driver) {
		new UserDAO().addUser(driver);
		String query = "INSERT INTO Driver (userId, licenseNo) VALUES (?, ?)"; //setting the foreign key and the license no
		
		try (Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) 
		{	
			statement.setInt(1, driver.getUserId()); 
			statement.setString(2, driver.getLicenseNo()); 
			statement.executeUpdate();	
			
			ResultSet rs = statement.getGeneratedKeys();	
			if (rs.next()) {
				driver.setDriverId(rs.getInt(1));
            }
			
			return driver;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
