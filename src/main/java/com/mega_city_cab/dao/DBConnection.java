package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mega_city_cab_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "asdf445447462";
	
	private static DBConnection instance;
	private Connection connection;
			
	private DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() {
		if (instance == null) {
			synchronized (DBConnection.class) {
				if (instance == null) {
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
//Uses singleton pattern - Ensures only one instance DBConnection is exists