package com.mega_city_cab.service;
import com.mega_city_cab.exception.AuthenticationException;

import org.mindrot.jbcrypt.BCrypt;

import com.mega_city_cab.dao.UserDAO;
import com.mega_city_cab.model.User;

public class AuthenticationService {
	private static AuthenticationService instance;
	private UserDAO userDAO;
	
	public AuthenticationService() {
		this.userDAO = new UserDAO();
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
	
	public User userLogin(String userName, String password) throws AuthenticationException {
		try {
			User user = userDAO.getUserByUserName(userName);

			if (user == null) {
				throw new AuthenticationException("User not found");
			}
			
			if (!BCrypt.checkpw(password, user.getPassword())) { //use this if you dont want to check hashed password "  user.getPassword().equals(password) "
				throw new AuthenticationException("Invalid credentials");
			}
			
			return user;
			
		} catch (Exception e) {
			throw  new AuthenticationException(e.getMessage());
		}
    }
}
