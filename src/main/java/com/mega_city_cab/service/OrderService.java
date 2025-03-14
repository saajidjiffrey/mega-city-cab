package com.mega_city_cab.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.mega_city_cab.dao.BookingDAO;
import com.mega_city_cab.dao.OrderDAO;
import com.mega_city_cab.dao.VehicleDAO;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Order;
import com.mega_city_cab.model.Vehicle;

public class OrderService {
	private static OrderService instance;
	private OrderDAO orderDAO;
	
	public OrderService() {
		this.orderDAO = new OrderDAO();
	}
	
	public static OrderService getInstance() {
		if (instance == null) {
			synchronized (OrderService.class) {
				if (instance == null) {
					instance = new OrderService();	
				}
			}
		}
		return instance;
	}
	
	//use DTOs
	public Order createOrder(Order order) throws Exception {
		try {
			return orderDAO.addOrder(order);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
