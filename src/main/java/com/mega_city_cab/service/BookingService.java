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
	public Booking createBooking(String pickupLocation, String destination, LocalDateTime bookingDatetime, int customerId) {
		String status = "booked";
		Booking newBooking = new Booking(pickupLocation, destination, bookingDatetime, status, customerId);
		return bookingDAO.addBooking(newBooking);
	}
	
	public boolean updateBookingStatus(int bookingId, String status) throws Exception {
		try {
			return bookingDAO.updateBookingStatus(bookingId, status);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteBooking(int bookingId) throws Exception {
		try {
			bookingDAO.deleteBooking(bookingId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Booking getBookingById(int bookingId) throws SQLException {
		try {
			return bookingDAO.getBookingById(bookingId);
		} catch (Exception e) {
			throw e;
		}
    }
	
	public List<Booking> getAllBookings() throws SQLException {
		try {
			return bookingDAO.getAllBookings();
		} catch (Exception e) {
			throw e;
		}
    }
	
	public List<Booking> getAllBookingsByCustomer(int customerId) throws SQLException {
		try {
			return bookingDAO.getAllBookingsByCustomer(customerId);
		} catch (Exception e) {
			throw e;
		}
    }

	public List<Booking> getAllBookingsByDriver(int driverId) throws SQLException {
		try {
			return bookingDAO.getAllBookingsByDriver(driverId);
		} catch (Exception e) {
			throw e;
		}
    }
	
	public List<Booking> getAllBookingsByStatus(String status) throws SQLException {
		try {
			return bookingDAO.getAllBookingsByStatus(status);
		} catch (Exception e) {
			throw e;
		}
    }
	
	public List<Booking> getAllBookingsByStatus(String status1, String status2) throws SQLException {
		try {
			return bookingDAO.getAllBookingsByStatus(status1, status2);
		} catch (Exception e) {
			throw e;
		}
    }
}
