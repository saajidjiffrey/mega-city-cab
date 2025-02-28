package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mega_city_cab.model.Driver;

public class DriverDAO {
	public void addDriver (Driver driver) {
		new UserDAO().addUser(driver);
		String query = "INSERT INTO Driver (userId, licenseNo) VALUES (?, ?)"; //setting the foreign key and the license no
		
		try {
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, driver.getUserId()); 
			statement.setString(2, driver.getLicenseNo()); 
			statement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
