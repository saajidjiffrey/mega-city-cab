package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mega_city_cab.model.Order;

public class OrderDAO {
	public Order addOrdeer (Order order) {
		String query = "INSERT INTO Orders (startTime, endTime, distance, fareAmount, bookingId, driverId, customerId) VALUES (? ,? ,? ,? ,?, ?, ?)"; 
		
		try (Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) 
		{		
			statement.setTimestamp(1, Timestamp.valueOf(order.getStartTime()));
			statement.setTimestamp(2, Timestamp.valueOf(order.getEndTime())  );
			statement.setDouble(3, order.getDistance());
			statement.setDouble(4, order.getFareAmount());
			statement.setInt(5, order.getBookingId());
			statement.setInt(6, order.getDriverId());
			statement.setInt(7, order.getCustomerId());
			
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				order.setBookingId(rs.getInt(1));
            }
			
			return order;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteOrder (int orderId) throws SQLException  {
		String query = "DELETE FROM Orders WHERE orderId = ?"; 
		
		try (Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) 
		{
			statement.setInt(1, orderId);
			int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new SQLException("No order found with ID: " + orderId);
	        }
			
		} catch (SQLException e) {
	        throw new SQLException("Error deleting order: " + e.getMessage(), e);
		}
	}
	
	public Order getOrderById(int orderId) throws SQLException {
        String query = "SELECT * FROM Orders WHERE orderId = ?";
        Order order;
        
        try (Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query)) 
        {	
        	statement.setInt(1,orderId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {

                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                double distance = resultSet.getDouble("distance");
                double fareAmount = resultSet.getDouble("fareAmount");
                int bookingId = resultSet.getInt("bookingId");
                int driverId = resultSet.getInt("driverId");
                int customerId = resultSet.getInt("customerId");

                order = new Order(orderId, startTime, endTime, distance, fareAmount, bookingId, driverId, customerId);
            } else {
                throw new SQLException("No Orders found with orderId: " + orderId);
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving orders: " + e.getMessage(), e);
		}
        
        return order;
    }

	public List<Order> getAllBookings() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try (Connection connection = DBConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) 
        {
            while (resultSet.next()) 
            {
            	int orderId = resultSet.getInt("orderId");
            	LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                double distance = resultSet.getDouble("distance");
                double fareAmount = resultSet.getDouble("fareAmount");
                int bookingId = resultSet.getInt("bookingId");
                int driverId = resultSet.getInt("driverId");
                int customerId = resultSet.getInt("customerId");
            	
            	orders.add(new Order(orderId, startTime, endTime, distance, fareAmount, bookingId, driverId, customerId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return orders;
    }
}
