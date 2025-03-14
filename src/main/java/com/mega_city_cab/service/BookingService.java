package com.mega_city_cab.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.mega_city_cab.dao.BookingDAO;
import com.mega_city_cab.model.Booking;

public class BookingService {
	private static BookingService instance;
	private BookingDAO bookingDAO;
	
	public BookingService() {
		this.bookingDAO = new BookingDAO();
	}
	
	public static BookingService getInstance() {
		if (instance == null) {
			synchronized (BookingService.class) {
				if (instance == null) {
					instance = new BookingService();
				}
			}
		}
		return instance;
	}
	
	//use DTOs
	public Booking createBooking(String pickupLocation, String destination, LocalDateTime bookingDatetime, int customerId, String pickupLat, String pickupLng, String destinationLat, String destinationLng, double estimatedTime, double distance, int vehicleId) throws Exception {
		try {
			String status = "booked";
			Booking newBooking = new Booking(pickupLocation, destination, bookingDatetime, status, customerId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId);
			return  bookingDAO.addBooking(newBooking);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
	
	public boolean updateBookingStatus(int bookingId, String status) throws Exception {
		try {
			return bookingDAO.updateBookingStatus(bookingId, status);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
	
	public void deleteBooking(int bookingId) throws Exception {
		try {
			bookingDAO.deleteBooking(bookingId);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
	
	public Booking getBookingById(int bookingId) throws Exception {
		try {
			return bookingDAO.getBookingById(bookingId);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }
	
	public List<Booking> getAllBookings() throws Exception {
		try {
			return bookingDAO.getAllBookings();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }
	
	public List<Booking> getAllBookingsByCustomer(int customerId) throws Exception {
		try {
			return bookingDAO.getAllBookingsByCustomer(customerId);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }

	public List<Booking> getAllBookingsByDriver(int driverId) throws Exception {
		try {
			return bookingDAO.getAllBookingsByDriver(driverId);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }
	
	public List<Booking> getAllBookingsByStatus(String status) throws Exception {
		try {
			return bookingDAO.getAllBookingsByStatus(status);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }
	
	public List<Booking> getAllBookingsByStatus(String status1, String status2) throws Exception {
		try {
			return bookingDAO.getAllBookingsByStatus(status1, status2);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }
	
	public List<Booking> getAllCompletedBookings() throws Exception {
		try {
			System.out.println("service");
			return bookingDAO.getAllCompletedBookings();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
    }
}
