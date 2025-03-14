package com.mega_city_cab.service;

import com.mega_city_cab.dao.AdminDAO;
import com.mega_city_cab.dao.CustomerDAO;
import com.mega_city_cab.exception.AuthenticationException;
import com.mega_city_cab.model.Admin;
import com.mega_city_cab.model.Customer;
import org.mindrot.jbcrypt.BCrypt;

public class AdminService {
	private static AdminService instance;
	private AdminDAO adminDAO;
	
	public AdminService() {
		this.adminDAO = new AdminDAO();
	}
	
	public static AdminService getInstance() {
		if (instance == null) {
			synchronized (AdminService.class) {
				if (instance == null) {
					instance = new AdminService();
				}
			}
		}
		return instance;
	}
	
	public Admin registerAdmin(Admin admin) throws Exception {
        try {
        	String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        	admin.setPassword(hashedPassword);
        	admin.setRole("ADMIN");
            return adminDAO.addAdmin(admin);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e); 
		}
    }
}
