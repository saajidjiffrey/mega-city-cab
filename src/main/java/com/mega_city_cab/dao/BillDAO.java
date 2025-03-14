package com.mega_city_cab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mega_city_cab.model.Bill;
import com.mega_city_cab.model.Order;

public class BillDAO {
	public Bill addBill (Bill bill) throws Exception {
		String query = "INSERT INTO Bill (totalAmount, tax, discount, finalAmount, paymentStatus, orderId) VALUES (? ,? ,? ,?, ?, ?)"; 
		
		try
		{	
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
					
			statement.setDouble(1, bill.getTotalAmount());
			statement.setDouble(2, bill.getTax());
			statement.setDouble(3, bill.getDiscount());
			statement.setDouble(4, bill.getFinalAmount());
			statement.setString(5, bill.getPaymentStatus());
			statement.setDouble(6, bill.getOrderId());
			
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				bill.setBillId(rs.getInt(1));	
            }
			
			return bill;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public boolean updateBillPaymementStatus (int billId, String status) throws Exception {
		String query = "UPDATE Bill SET paymentStatus = ? WHERE billId = ?"; 
		
		try
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, status );
			statement.setInt(2, billId);
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected == 0) {
	            throw new SQLException("No bill found with ID: " + billId);
	        }
			
	        return rowsAffected > 0; 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Database error: " + e.getMessage(), e);
		}
	}
	
	public void deleteBill (int billId) throws Exception  {
		String query = "DELETE FROM Bill WHERE billId = ?"; 
		
		try 
		{
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
					
			statement.setInt(1, billId);
			int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new SQLException("No bill found with ID: " + billId);
	        }
			
		} catch (Exception e) {
	        throw new SQLException("Error deleting bill: " + e.getMessage(), e);
		}
	}
	
	public Bill getBillById(int billId) throws Exception {
        String query = "SELECT * FROM Bill WHERE billId = ?";
        Bill bill;
        
        try 
        {	
        	Connection connection = DBConnectionFactory.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query);
        			
        	statement.setInt(1,billId );            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {

                double totalAmount = resultSet.getDouble("totalAmount");
                double tax = resultSet.getDouble("tax");
                double discount = resultSet.getDouble("discount");
                double finalAmount = resultSet.getDouble("finalAmount");
                String paymentStatus = resultSet.getString("paymentStatus");
                int orderId = resultSet.getInt("orderId");

                bill = new Bill(billId, totalAmount, tax, discount, finalAmount, paymentStatus, orderId);
            } else {
                throw new SQLException("No Bill found with billId: " + billId);
            }
		} catch (Exception e) {
			throw new Exception("Error retrieving bills: " + e.getMessage(), e);
		}
        
        return bill;
    }

	public List<Bill> getAllBills() throws Exception {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Bill";

        try 
        {
        	Connection connection = DBConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) 
            {
            	int billId = resultSet.getInt("billId");
            	double totalAmount = resultSet.getDouble("totalAmount");
                double tax = resultSet.getDouble("tax");
                double discount = resultSet.getDouble("discount");
                double finalAmount = resultSet.getDouble("finalAmount");
                String paymentStatus = resultSet.getString("paymentStatus");
                int orderId = resultSet.getInt("orderId");
            	
            	bills.add(new Bill(billId, totalAmount, tax, discount, finalAmount, paymentStatus, orderId));
            }
		} catch (Exception e) {
			throw new SQLException("Error retrieving bills: " + e.getMessage(), e);
		}
        
        return bills;
    }
}
