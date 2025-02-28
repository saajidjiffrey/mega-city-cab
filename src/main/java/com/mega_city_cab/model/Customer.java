package com.mega_city_cab.model;

public class Customer extends User{
	private int customerId;

	public Customer( int userId, String name, String address, String NIC, String phone, String email, String username, String password, int customerId) {
		super(	userId, name, address, NIC, phone, email, username, password, "CUSTOMER");
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
