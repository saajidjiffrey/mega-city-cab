package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.mega_city_cab.model.Admin;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Customer;

public class AdminDAO {
	public Admin addAdmin (Admin admin ) throws Exception{
		new UserDAO().addUser(admin); 
		String query = "INSERT INTO Admin (userId) VALUES (?)"; //setting the foreign key
		
		try  
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, admin.getUserId()); 
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating admin failed, no rows affected.");
	        }
			
			//assigns and return the customer with the assigned userId
			ResultSet rs = statement.getGeneratedKeys();	
			if (rs.next()) {
				admin.setAdminId(rs.getInt(1));
	        } else {
	            throw new SQLException("Creating admin failed, no ID obtained.");
	        }
			
			return admin; 
		} catch (SQLException e) {
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public Admin getAdminByID(int userId) throws Exception {
		String query = "SELECT u.userId, u.name, u.address, u.NIC, u.phone, u.email, u.userName, u.password, u.role, " +
                "a.adminId " +
                "FROM User u " +
                "JOIN Admin a ON u.userId = a.userId " +
                "WHERE u.userId = ?";
		Admin admin = null;
        
        try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
        	statement.setInt(1,userId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	admin = new Admin(
                        resultSet.getInt("userId"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("NIC"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getInt("adminId")
                    );
                } else {
                throw new Exception("No admin found with userId: " + userId);
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving admin: " + e.getMessage(), e);
		}
        
        return admin;
    }
}
