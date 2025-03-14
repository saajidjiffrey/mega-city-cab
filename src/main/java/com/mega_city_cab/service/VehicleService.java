package com.mega_city_cab.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.mega_city_cab.dao.BookingDAO;
import com.mega_city_cab.dao.VehicleDAO;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Vehicle;

public class VehicleService {
	private static VehicleService instance;
	private VehicleDAO vehicleDAO;
	
	public VehicleService() {
		this.vehicleDAO = new VehicleDAO();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			synchronized (VehicleService.class) {
				if (instance == null) {
					instance = new VehicleService();	
				}
			}
		}
		return instance;
	}
	
	//use DTOs
	public Vehicle createVehicle(int passengerCount, double pricePerKm, String vehicleType, double taxPercentage, double discountPercentage) {
		Vehicle newVehicle = new Vehicle(passengerCount, pricePerKm, vehicleType, taxPercentage, discountPercentage);
		return vehicleDAO.addVehicle(newVehicle);
	}
	
	public boolean updatePricePerKm(int vehicleId, double pricePerKm) throws Exception {
		try {
			return vehicleDAO.updatePricePerKm(vehicleId, pricePerKm);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteVehicle(int vehicleId) throws Exception {
		try {
			vehicleDAO.deleteVehicle(vehicleId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Vehicle getVehicleById(int vehicleId) throws SQLException {
		try {
			return vehicleDAO.getVehicleById(vehicleId);
		} catch (Exception e) {
			throw e;
		}
    }
	
	public List<Vehicle> getAllVehicles() throws SQLException {
		try {
			return vehicleDAO.getAllVehicles();
		} catch (Exception e) {
			throw e;
		}
    }
}
