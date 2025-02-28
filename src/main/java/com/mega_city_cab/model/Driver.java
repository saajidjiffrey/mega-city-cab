package com.mega_city_cab.model;

public class Driver extends User{
	private int driverId;
	private int licenseNo;
	
	public Driver(int userId, String name, String address, String NIC, String phone, String email, String username, String password, int driverId, int licenseNo) {
		super(userId, name, address, NIC, phone, email, username, password, "DRIVER");
		this.driverId = driverId;
		this.licenseNo = licenseNo;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(int licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	
}
