package com.mega_city_cab.service;

import com.mega_city_cab.dao.CustomerDAO;
import com.mega_city_cab.model.Customer;
import org.mindrot.jbcrypt.BCrypt;

public class CustomerService {
	private static CustomerService instance;
	private CustomerDAO customerDAO;
	
	public CustomerService() {
		this.customerDAO = new CustomerDAO();
	}
	
	public static CustomerService getInstance() {
		if (instance == null) {
			synchronized (CustomerService.class) {
				if (instance == null) {
					instance = new CustomerService();
				}
			}
		}
		return instance;
	}
	
	public Customer registerCustomer(Customer customer) {
        String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()); // Hash password before saving
        customer.setPassword(hashedPassword);
        return customerDAO.addCustomer(customer);
    }
}
