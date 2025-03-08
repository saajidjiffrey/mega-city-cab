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

import com.mega_city_cab.model.Booking;

public class BookingDAO {
	public Booking addBooking (Booking booking) {
		String query = "INSERT INTO Booking (pickupLocation, destination, bookingDatetime, status, customerId) VALUES (? ,? ,? ,? ,? )"; 
		
		try {
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, booking.getPickupLocation() );
			statement.setString(2, booking.getDestination());
			statement.setTimestamp(3, Timestamp.valueOf(booking.getBookingDatetime()) );
			statement.setString(4, booking.getStatus());
			statement.setInt(5, booking.getCustomerId());
			
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				booking.setBookingId(rs.getInt(1));
            }
			
			return booking;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateBookingStatus (int bookingId, String status) throws SQLException {
		String query = "UPDATE Booking SET status = ? WHERE bookingId = ?"; 
		
		try (Connection connection = DBConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) 
		{
			statement.setString(1, status );
			statement.setInt(2, bookingId);
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 0) {
	            throw new SQLException("No booking found with ID: " + bookingId);
	        }
			
	        return rowsAffected > 0; 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteBooking (int bookingId) throws SQLException  {
		String query = "DELETE FROM Booking WHERE bookingId = ?"; 
		
		try (Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) 
		{
			statement.setInt(1, bookingId);
			int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new SQLException("No booking found with ID: " + bookingId);
	        }
			
		} catch (SQLException e) {
	        throw new SQLException("Error deleting booking: " + e.getMessage(), e);
		}
	}
	
	public Booking getBookingById(int bookingId) throws SQLException {
        String query = "SELECT * FROM Booking WHERE bookingId = ?";
        Booking booking;
        
        try (Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query)) 
        {	
        	statement.setInt(1,bookingId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String pickupLocation = resultSet.getString("pickupLocation");
                String destination = resultSet.getString("destination");
                LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
                String status = resultSet.getString("status");
                int driverId = resultSet.getInt("driverId");
                int customerId = resultSet.getInt("customerId");

                booking = new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId);
            } else {
                throw new SQLException("No booking found with bookingId: " + bookingId);
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return booking;
    }

	public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking";

        try (Connection connection = DBConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) 
        {
            while (resultSet.next()) 
            {
            	int bookingId = resultSet.getInt("bookingId");
            	String pickupLocation = resultSet.getString("pickupLocation");
            	String destination = resultSet.getString("destination");
            	LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
            	String status = resultSet.getString("status");
            	int customerId = resultSet.getInt("customerId");
            	int driverId = resultSet.getInt("driverId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }

	public List<Booking> getAllBookingsByCustomer(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE customerId = ?";

        try (Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query)) 
        {	
        	statement.setInt(1,customerId );            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) 
            {
            	int bookingId = resultSet.getInt("bookingId");
            	String pickupLocation = resultSet.getString("pickupLocation");
            	String destination = resultSet.getString("destination");
            	LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
            	String status = resultSet.getString("status");
            	int driverId = resultSet.getInt("driverId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }
	
	public List<Booking> getAllBookingsByDriver(int driverId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE driverId = ?";

        try (Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query))
        {	
        	statement.setInt(1,driverId );            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) 
            {
            	int bookingId = resultSet.getInt("bookingId");
            	String pickupLocation = resultSet.getString("pickupLocation");
            	String destination = resultSet.getString("destination");
            	LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
            	String status = resultSet.getString("status");
            	int customerId = resultSet.getInt("customerId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }
	
	public List<Booking> getAllBookingsByStatus(String status) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE status = ?";

        try (Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query)) 
        {	
        	statement.setString(1,status );            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) 
            {
            	int bookingId = resultSet.getInt("bookingId");
            	String pickupLocation = resultSet.getString("pickupLocation");
            	String destination = resultSet.getString("destination");
            	LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
            	int driverId = resultSet.getInt("driverId");
            	int customerId = resultSet.getInt("customerId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }
	
	public List<Booking> getAllBookingsByStatus(String status1, String status2) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE status IN (?, ?)";

        try (Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query)) 
        {	
        	statement.setString(1,status1 );     
        	statement.setString(2,status2 );        
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) 
            {
            	int bookingId = resultSet.getInt("bookingId");
            	String pickupLocation = resultSet.getString("pickupLocation");
            	String destination = resultSet.getString("destination");
            	LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
            	String status = resultSet.getString("status");
            	int driverId = resultSet.getInt("driverId");
            	int customerId = resultSet.getInt("customerId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }

}
