package com.mega_city_cab.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.mega_city_cab.dao.BillDAO;
import com.mega_city_cab.dao.BookingDAO;
import com.mega_city_cab.model.Booking;

public class BillService {
	private static BillService instance;
	private BillDAO billDAO;
	
	public BillService() {
		this.billDAO = new BillDAO();
	}
	
	public static BillService getInstance() {
		if (instance == null) {
			synchronized (BillService.class) {
				if (instance == null) {
					instance = new BillService();	
				}
			}
		}
		return instance;
	}
	
	public boolean updateBillPaymentStatus(int billId, String status) throws Exception {
		try {
			boolean success = billDAO.updateBillPaymementStatus(billId, status);
			
			return success;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
	
}
