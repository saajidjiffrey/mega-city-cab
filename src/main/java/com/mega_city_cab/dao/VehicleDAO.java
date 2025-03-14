package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mega_city_cab.model.Vehicle;

public class VehicleDAO {
	public Vehicle addVehicle(Vehicle vehicle) {
		String query = "INSERT INTO Vehicle (passengerCount, pricePerKm, vehicleType, taxPercentage, discountPercentage) VALUES (? ,? ,?, ?, ? )"; 
		
		try
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					
			statement.setInt(1, vehicle.getPassengerCount());
			statement.setDouble(2, vehicle.getPricePerKm());
			statement.setString(3, vehicle.getVehicleType());
			statement.setDouble(4, vehicle.getTaxPercentage());
			statement.setDouble(5, vehicle.getDiscountPercentage());
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				vehicle.setVehicleId(rs.getInt(1));	
            }
			
			return vehicle;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updatePricePerKm(int vehicleId, double pricePerKm) throws SQLException {
		String query = "UPDATE Vehicle SET pricePerKm = ? WHERE billId = ?"; 
		
		try
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setDouble(1, pricePerKm );
			statement.setInt(2, vehicleId);
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 0) {
	            throw new SQLException("No vehcile found with ID: " + vehicleId);
	        }
			
	        return rowsAffected > 0; 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteVehicle (int vehicleId) throws SQLException  {
		String query = "DELETE FROM Vehicle WHERE vehicleId = ?"; 
		
		try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
					
			statement.setInt(1, vehicleId);
			int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new SQLException("No vehicle found with ID: " + vehicleId);
	        }
			
		} catch (SQLException e) {
	        throw new SQLException("Error deleting vehicle: " + e.getMessage(), e);
		}
	}
	
	public Vehicle getVehicleById(int vehicleId) throws SQLException {
        String query = "SELECT * FROM Vehicle WHERE vehicleId = ?";
        Vehicle vehicle;
        
        try 
        {	
        	Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query);
        			
        	statement.setInt(1,vehicleId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {

                int passengerCount = resultSet.getInt("passengerCount");
                double pricePerKm = resultSet.getDouble("pricePerKm");
                String vehicleType = resultSet.getString("vehicleType");
                double taxPercentage = resultSet.getDouble("taxPercentage");
                double discountPercentage = resultSet.getDouble("discountPercentage");

                vehicle = new Vehicle(vehicleId, passengerCount, pricePerKm, vehicleType, taxPercentage, discountPercentage);
            } else {
                throw new SQLException("No Vehicle found with billId: " + vehicleId);
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bills: " + e.getMessage(), e);
		}
        
        return vehicle;
    }

	public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle";

        try 
        {
        	Connection connection = DBConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) 
            {
            	int vehicleId = resultSet.getInt("vehicleId");
            	int passengerCount = resultSet.getInt("passengerCount");
                double pricePerKm = resultSet.getDouble("pricePerKm");
                String vehicleType = resultSet.getString("vehicleType");
                double taxPercentage = resultSet.getDouble("taxPercentage");
                double discountPercentage = resultSet.getDouble("discountPercentage");
            	
                vehicles.add(new Vehicle(vehicleId, passengerCount, pricePerKm, vehicleType, taxPercentage, discountPercentage));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bills: " + e.getMessage(), e);
		}
        
        return vehicles;
    }
}
