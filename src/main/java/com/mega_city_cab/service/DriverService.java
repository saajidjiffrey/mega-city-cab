package com.mega_city_cab.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.mega_city_cab.dao.DriverDAO;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Driver;

public class DriverService {
	private static DriverService instance;
	private DriverDAO driverDAO;
	
	public DriverService() {
		this.driverDAO = new DriverDAO();
	}
	
	public static DriverService getInstance() {
		if (instance == null) {
			synchronized (DriverService.class) {
				if (instance == null) {
					instance = new DriverService();
				}
			}
		}
		return instance;
	}
	
	public Driver registerDriver(Driver driver) throws Exception {
		try {
			String hashedPassword = BCrypt.hashpw(driver.getPassword(), BCrypt.gensalt());
	        driver.setPassword(hashedPassword);
	        driver.setRole("DRIVER");
	        return driverDAO.addDriver(driver);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}        
    }
	
	public List<Driver> getAllDrivers() throws Exception {
	    try {
	        return driverDAO.getAllDrivers(); 
	    } catch (Exception e) {
	        throw new Exception("Error in service layer: " + e.getMessage(), e);
	    }
	}

}
