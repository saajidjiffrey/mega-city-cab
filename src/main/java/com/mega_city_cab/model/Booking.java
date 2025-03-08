package com.mega_city_cab.model;

import java.time.LocalDateTime;

public class Booking {
	private int bookingId;
	private int customerId;
	private int driverId;
	private String pickupLocation;
	private String destination;
	private LocalDateTime bookingDatetime;
	private String status;

	///Full Constructor
	public Booking(int bookingId, String pickupLocation, String destination, LocalDateTime bookingDatetime, String status, int customerId, int driverId) {
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.driverId = driverId;
		this.pickupLocation = pickupLocation;	
		this.destination = destination;
		this.bookingDatetime = bookingDatetime;
		this.status = status;
	}
	
	//no bookingId, driverId initially
	public Booking( String pickupLocation, String destination, LocalDateTime bookingDatetime, String status, int customerId) {
		this.customerId = customerId;
		this.pickupLocation = pickupLocation;	
		this.destination = destination;
		this.bookingDatetime = bookingDatetime;
		this.status = status;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getBookingDatetime() {
		return bookingDatetime;
	}

	public void setBookingDatetime(LocalDateTime bookingDatetime) {
		this.bookingDatetime = bookingDatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
