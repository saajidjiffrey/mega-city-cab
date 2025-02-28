package com.mega_city_cab.model;

public class Driver extends User{
	private int driverId;
	private String licenseNo;
	
	public Driver(int userId, String name, String address, String NIC, String phone, String email, String userName, String password, int driverId, String licenseNo) {
		super(userId, name, address, NIC, phone, email, userName, password, "DRIVER");
		this.driverId = driverId;
		this.licenseNo = licenseNo;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	
}
