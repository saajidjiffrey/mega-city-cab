package com.mega_city_cab.model;

//@JsonIgnoreProperties(ignoreUnknown = true)  // Prevents errors if extra fields are sent in JSON (use this if you want)
public class Driver extends User{
	private int driverId;
	private String licenseNo;
	
	// Full Constructor
	public Driver(int userId, String name, String address, String NIC, String phone, String email, String userName, String password, int driverId, String licenseNo) {
		super(userId, name, address, NIC, phone, email, userName, password, "DRIVER");
		this.driverId = driverId;
		this.licenseNo = licenseNo;
	}
	
	public Driver(int userId, String name, String address, String NIC, String phone, String email, String userName, String password,String role, int driverId, String licenseNo) {
		super(userId, name, address, NIC, phone, email, userName, password, role);
		this.driverId = driverId;
		this.licenseNo = licenseNo;
	}
	
	// Constructor for new customer (No userId initially)
	public Driver(String name, String address, String NIC, String phone, String email, String userName, String password, String licenseNo, String role) {
		super(name, address, NIC, phone, email, userName, password, role);
		this.licenseNo = licenseNo;
	}
	
	public Driver() {
	    super();
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
