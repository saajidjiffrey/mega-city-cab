package com.mega_city_cab.model;

public class User {
	private int userId;
	private String name;
	private String address;
	private String NIC;
	private String phone;
	private String email;
	private String userName;
	private String password;
	private String role;

	public User( String name, String address, String NIC, String phone, String email, String userName, String password, String role) {
		super();
		this.name = name;
		this.address = address;
		this.NIC = NIC;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public User( int userId, String name, String address, String NIC, String phone, String email, String userName, String password, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.NIC = NIC;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
