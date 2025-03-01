package com.mega_city_cab.model;

//@JsonIgnoreProperties(ignoreUnknown = true)  // Prevents errors if extra fields are sent in JSON (use this if you want)
public class Customer extends User{
	private int customerId;

	// Full Constructor
	public Customer( int userId, String name, String address, String NIC, String phone, String email, String userName, String password, int customerId) {
		super(	userId, name, address, NIC, phone, email, userName, password, "CUSTOMER");
		this.customerId = customerId;
	}	
	
	// Constructor for new customer (No userId initially)
	public Customer( String name, String address, String NIC, String phone, String email, String userName, String password) {
		super( name, address, NIC, phone, email, userName, password, "CUSTOMER");
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
