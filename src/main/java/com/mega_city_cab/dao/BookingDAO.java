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

import com.mega_city_cab.model.Bill;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Order;

public class BookingDAO {
	public Booking addBooking (Booking booking) throws Exception{ 
		String query = "INSERT INTO Booking (pickupLocation, destination, bookingDatetime, status, customerId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId  ) VALUES (? ,? ,? ,? ,?, ? ,? ,? ,? ,?,? ,? )"; 
		
		try 
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, booking.getPickupLocation() );
			statement.setString(2, booking.getDestination());
			statement.setTimestamp(3, Timestamp.valueOf(booking.getBookingDatetime()) );
			statement.setString(4, booking.getStatus());
			statement.setInt(5, booking.getCustomerId());
			statement.setString(6, booking.getPickupLat());
			statement.setString(7, booking.getPickupLng());
			statement.setString(8, booking.getDestinationLat());
			statement.setString(9, booking.getDestinationLng());
			statement.setDouble(10, booking.getEstimatedTime());
			statement.setDouble(11, booking.getDistance());
			statement.setInt(12, booking.getVehicleId());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating Booking failed, no rows affected.");
	        }
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				booking.setBookingId(rs.getInt(1));
            } else {
	            throw new SQLException("Creating booking failed, no ID obtained.");
	        }
			
			return booking;
		} catch (SQLException e) {
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public boolean updateBookingStatus (int bookingId, String status) throws Exception {
		String query = "UPDATE Booking SET status = ? WHERE bookingId = ?"; 
		
		try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, status );
			statement.setInt(2, bookingId);
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 0) {
	            throw new SQLException("No booking found with ID: " + bookingId);
	        }
			
	        return rowsAffected > 0; 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public void deleteBooking (int bookingId) throws Exception  {
		String query = "DELETE FROM Booking WHERE bookingId = ?"; 
		
		try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, bookingId);
			int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new SQLException("No booking found with ID: " + bookingId);
	        }
			
		} catch (Exception e) {
	        throw new Exception("Error deleting booking: " + e.getMessage(), e);
		}
	}
	
	public Booking getBookingById(int bookingId) throws Exception {
        String query = "SELECT * FROM Booking WHERE bookingId = ?";
        Booking booking;
        
        try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
        	statement.setInt(1,bookingId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String pickupLocation = resultSet.getString("pickupLocation");
                String destination = resultSet.getString("destination");
                LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
                String status = resultSet.getString("status");
                int driverId = resultSet.getInt("driverId");
                int customerId = resultSet.getInt("customerId");
                
                String pickupLat = resultSet.getString("pickupLat");
                String pickupLng = resultSet.getString("pickupLng");
                String destinationLat = resultSet.getString("destinationLat");
                String destinationLng = resultSet.getString("destinationLng");
                double estimatedTime = resultSet.getDouble("estimatedTime");
                double distance = resultSet.getDouble("distance");
                int vehicleId = resultSet.getInt("vehicleId");

                booking = new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId);
            } else {
                throw new Exception("No booking found with bookingId: " + bookingId);
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return booking;
    }

	public List<Booking> getAllBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking";

        try 
        {
        	Connection connection = DBConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query); 
            
            while (resultSet.next()) 
            {
            	int bookingId = resultSet.getInt("bookingId");
            	String pickupLocation = resultSet.getString("pickupLocation");
            	String destination = resultSet.getString("destination");
            	LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
            	String status = resultSet.getString("status");
            	int customerId = resultSet.getInt("customerId");
            	int driverId = resultSet.getInt("driverId");
            	
            	String pickupLat = resultSet.getString("pickupLat");
                String pickupLng = resultSet.getString("pickupLng");
                String destinationLat = resultSet.getString("destinationLat");
                String destinationLng = resultSet.getString("destinationLng");
                double estimatedTime = resultSet.getDouble("estimatedTime");
                double distance = resultSet.getDouble("distance");
                int vehicleId = resultSet.getInt("vehicleId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId));
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }

	public List<Booking> getAllBookingsByCustomer(int customerId) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE customerId = ?";

        try  
        {	
        	Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query);
        			
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
            	
            	String pickupLat = resultSet.getString("pickupLat");
                String pickupLng = resultSet.getString("pickupLng");
                String destinationLat = resultSet.getString("destinationLat");
                String destinationLng = resultSet.getString("destinationLng");
                double estimatedTime = resultSet.getDouble("estimatedTime");
                double distance = resultSet.getDouble("distance");
                int vehicleId = resultSet.getInt("vehicleId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId));
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }
	
	public List<Booking> getAllBookingsByDriver(int driverId) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE driverId = ?";

        try 
        {	
        	Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query);
        	
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
            	
            	String pickupLat = resultSet.getString("pickupLat");
                String pickupLng = resultSet.getString("pickupLng");
                String destinationLat = resultSet.getString("destinationLat");
                String destinationLng = resultSet.getString("destinationLng");
                double estimatedTime = resultSet.getDouble("estimatedTime");
                double distance = resultSet.getDouble("distance");
                int vehicleId = resultSet.getInt("vehicleId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId));
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }
	
	public List<Booking> getAllBookingsByStatus(String status) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE status = ?";

        try 
        {	
        	Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query);
        	
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
            	
            	String pickupLat = resultSet.getString("pickupLat");
                String pickupLng = resultSet.getString("pickupLng");
                String destinationLat = resultSet.getString("destinationLat");
                String destinationLng = resultSet.getString("destinationLng");
                double estimatedTime = resultSet.getDouble("estimatedTime");
                double distance = resultSet.getDouble("distance");
                int vehicleId = resultSet.getInt("vehicleId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId));
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }
	
	public List<Booking> getAllBookingsByStatus(String status1, String status2) throws Exception {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Booking WHERE status IN (?, ?)";

        try 
        {	
        	Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query);
        	
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
            	
            	String pickupLat = resultSet.getString("pickupLat");
                String pickupLng = resultSet.getString("pickupLng");
                String destinationLat = resultSet.getString("destinationLat");
                String destinationLng = resultSet.getString("destinationLng");
                double estimatedTime = resultSet.getDouble("estimatedTime");
                double distance = resultSet.getDouble("distance");
                int vehicleId = resultSet.getInt("vehicleId");
            	
            	bookings.add(new Booking(bookingId, pickupLocation, destination, bookingDateTime, status, customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId));
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bookings: " + e.getMessage(), e);
		}
        
        return bookings;
    }

	public List<Booking> getAllCompletedBookings() throws Exception {
		List<Booking> bookings = new ArrayList<>();
		String query = "SELECT "
			    + "b.bookingId, b.pickupLocation, b.destination, b.bookingDateTime, b.driverId, b.customerId, "
			    + "b.pickupLat, b.pickupLng, b.destinationLat, b.destinationLng, b.estimatedTime, b.distance, b.vehicleId, "
			    + "o.orderId, o.startTime, o.endTime, o.distance AS orderDistance, o.fareAmount, o.bookingId AS OrderBookingId, o.driverId AS OrderDriverId, o.customerId AS OrderCustomerId, "
			    + "bi.billId, bi.orderId AS billOrderId, bi.totalAmount, bi.tax, "
			    + "bi.discount, bi.finalAmount, bi.paymentStatus "
			    + "FROM Booking b "
			    + "JOIN Orders o ON b.bookingId = o.bookingId "
			    + "JOIN Bill bi ON o.orderId = bi.orderId "
			    + "WHERE b.status = 'completed'";


		try  {
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int bookingId = resultSet.getInt("bookingId");
				String pickupLocation = resultSet.getString("pickupLocation");
				String destination = resultSet.getString("destination");
				LocalDateTime bookingDateTime = resultSet.getTimestamp("bookingDateTime").toLocalDateTime();
				int driverId = resultSet.getInt("driverId");
				int customerId = resultSet.getInt("customerId");

				String pickupLat = resultSet.getString("pickupLat");
				String pickupLng = resultSet.getString("pickupLng");
				String destinationLat = resultSet.getString("destinationLat");
				String destinationLng = resultSet.getString("destinationLng");
				double estimatedTime = resultSet.getDouble("estimatedTime");
				double distance = resultSet.getDouble("distance");
				int vehicleId = resultSet.getInt("vehicleId");

				// Extract Order and Bill details from the result set
				int orderId = resultSet.getInt("orderId");
				LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
				LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
//	            double distanceO = resultSet.getDouble("distance");
				double fareAmount = resultSet.getDouble("fareAmount");

				Order order = new Order(orderId, startTime, endTime, distance, fareAmount, bookingId, driverId,
						customerId);

				// Bill details
				int billId = resultSet.getInt("billId");
				double totalAmount = resultSet.getDouble("totalAmount");
				double tax = resultSet.getDouble("tax");
				double discount = resultSet.getDouble("discount");
				double finalAmount = resultSet.getDouble("finalAmount");
				String paymentStatus = resultSet.getString("paymentStatus");

				// Create Bill object
				Bill bill = new Bill(billId, totalAmount, tax, discount, finalAmount, paymentStatus, orderId);

				// Create Booking object
				Booking booking = new Booking(bookingId, pickupLocation, destination, bookingDateTime, "completed",
						customerId, driverId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime,
						distance, vehicleId, order, bill);
//				booking.setOrder(order); // Set Order
//				booking.setBill(bill); // Set Bill
				bookings.add(booking);
			}
		} catch (Exception e) {
			throw new Exception("Error retrieving completed bookings: " + e.getMessage(), e);
		}

		return bookings;
	}

	private Order getOrderByBookingId(int bookingId, Connection connection) throws Exception {
	    String query = "SELECT * FROM Orders WHERE bookingId = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, bookingId);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            int orderId = resultSet.getInt("orderId");
	            LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                double distance = resultSet.getDouble("distance");
                double fareAmount = resultSet.getDouble("fareAmount");
                int driverId = resultSet.getInt("driverId");
                int customerId = resultSet.getInt("customerId");
	            
	            // Return a new Order object
	            return new Order(orderId, startTime, endTime, distance, fareAmount, bookingId, driverId, customerId);
	        } else {
	            return null;
	        }
	    }
	}
	
	private Bill getBillByOrderId(int orderId, Connection connection) throws Exception {
	    String query = "SELECT * FROM Bill WHERE orderId = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, orderId);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	        	int billId = resultSet.getInt("billId");
            	double totalAmount = resultSet.getDouble("totalAmount");
                double tax = resultSet.getDouble("tax");
                double discount = resultSet.getDouble("discount");
                double finalAmount = resultSet.getDouble("finalAmount");
                String paymentStatus = resultSet.getString("paymentStatus");
	            
	            // Return a new Bill object
	            return new Bill(billId, totalAmount, tax, discount, finalAmount, paymentStatus, orderId);
	        } else {
	            return null;
	        }
	    }
	}
}
