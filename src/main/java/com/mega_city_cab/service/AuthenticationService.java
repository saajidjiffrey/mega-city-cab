package com.mega_city_cab.service;
import com.mega_city_cab.exception.AuthenticationException;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.mega_city_cab.dao.AdminDAO;
import com.mega_city_cab.dao.CustomerDAO;
import com.mega_city_cab.dao.DriverDAO;
import com.mega_city_cab.dao.UserDAO;
import com.mega_city_cab.model.Admin;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Driver;
import com.mega_city_cab.model.User;

public class AuthenticationService {
	private static AuthenticationService instance;
	private UserDAO userDAO;
	private CustomerDAO customerDAO;
	private DriverDAO driverDAO;
	private AdminDAO adminDAO;
	
	public AuthenticationService() {
		this.userDAO = new UserDAO();
		this.customerDAO = new CustomerDAO(); 
		this.driverDAO = new DriverDAO();
		this.adminDAO = new AdminDAO();
	}
	
	public static AuthenticationService getInstance() {
		if (instance == null) {
			synchronized (AuthenticationService.class) {
				if (instance == null) {
					instance = new AuthenticationService();
				}
			}
		}
		return instance;
	}
	
	public User userLogin(String userName, String password) throws AuthenticationException, Exception {
	    try {
	        User user = userDAO.getUserByUserName(userName);

	        if (user == null) {
	            throw new Exception("User not found");
	        }

	        if (!BCrypt.checkpw(password, user.getPassword())) {
	            throw new Exception("Invalid username or password");
	        }

	        // Identify whether user is a Customer or a Driver and get the respective ID
	        if ("CUSTOMER".equalsIgnoreCase(user.getRole())) {
	            Customer customer = customerDAO.getCustomerByUserId(user.getUserId()); 
	            if (customer != null) {
	                return customer;
	            }
	        } else if ("DRIVER".equalsIgnoreCase(user.getRole())) {
	            Driver driver = driverDAO.getDriverByUserId(user.getUserId()); 
	            if (driver != null) {
	                return driver; 
	            }
	        } else if ("ADMIN".equalsIgnoreCase(user.getRole())) {
	            Admin admin = adminDAO.getAdminByID(user.getUserId()); 
	            if (admin != null) {
	                return admin; 
	            }
	        }

	        return user;

	    } catch (Exception e) {
	        throw new Exception("Auth Error: " + e.getMessage(), e); 
	    }
	}
}
