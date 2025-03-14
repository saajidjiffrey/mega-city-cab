package com.mega_city_cab.model;

//@JsonIgnoreProperties(ignoreUnknown = true)  // Prevents errors if extra fields are sent in JSON (use this if you want)
public class Admin extends User{
	private int adminId;

	// Full Constructor
	public Admin( int userId, String name, String address, String NIC, String phone, String email, String userName, String password, int adminId) {
		super(	userId, name, address, NIC, phone, email, userName, password, "ADMIN");
		this.adminId = adminId;
	}	
	
	public Admin( int userId, String name, String address, String NIC, String phone, String email, String userName, String password, String role, int adminId) {
		super(	userId, name, address, NIC, phone, email, userName, password,role);
		this.adminId = adminId;
	}	
	
	// Constructor for new admin (No userId initially)
	public Admin( String name, String address, String NIC, String phone, String email, String userName, String password, String role) {
		super( name, address, NIC, phone, email, userName, password, role);
	}
	
	public Admin() {
	    super();
	}
	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
}
