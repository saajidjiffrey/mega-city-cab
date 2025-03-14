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
	private String pickupLat;
	private String pickupLng;
	private String destinationLat;
	private String destinationLng;
	private Double estimatedTime;
	private Double distance;
	private int vehicleId;
	
	private Order order;  // New field for Order
    private Bill bill;    // New field for Bill
	
	///Full Constructor
	public Booking(int bookingId, String pickupLocation, String destination, LocalDateTime bookingDatetime,
			String status, int customerId, int driverId, String pickupLat, String pickupLng, String destinationLat,
			String destinationLng, double estimatedTime, double distance, int vehicleId) {
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.driverId = driverId;
		this.pickupLocation = pickupLocation;	
		this.destination = destination;
		this.bookingDatetime = bookingDatetime;
		this.status = status;
		this.pickupLat = pickupLat;
		this.pickupLng = pickupLng;
		this.destinationLat = destinationLat;
		this.destinationLng = destinationLng;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.vehicleId = vehicleId;
	}
	
	//no bookingId, driverId initially
	public Booking( String pickupLocation, String destination, LocalDateTime bookingDatetime, String status, int customerId, String pickupLat, String pickupLng, String destinationLat, String destinationLng, double estimatedTime, double distance, int vehicleId) {
		this.customerId = customerId;
		this.pickupLocation = pickupLocation;	
		this.destination = destination;
		this.bookingDatetime = bookingDatetime;
		this.status = status;
		this.pickupLat = pickupLat;
		this.pickupLng = pickupLng;
		this.destinationLat = destinationLat;
		this.destinationLng = destinationLng;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.vehicleId = vehicleId;
	}
	
	//with orders and bills
	public Booking(int bookingId, String pickupLocation, String destination, LocalDateTime bookingDatetime,
			String status, int customerId, int driverId, String pickupLat, String pickupLng, String destinationLat,
			String destinationLng, double estimatedTime, double distance, int vehicleId, Order order, Bill bill) {
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.driverId = driverId;
		this.pickupLocation = pickupLocation;	
		this.destination = destination;
		this.bookingDatetime = bookingDatetime;
		this.status = status;
		this.pickupLat = pickupLat;
		this.pickupLng = pickupLng;
		this.destinationLat = destinationLat;
		this.destinationLng = destinationLng;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.vehicleId = vehicleId;
		this.order = order;
		this.bill = bill;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
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

	public String getPickupLat() {
		return pickupLat;
	}

	public void setPickupLat(String pickupLat) {
		this.pickupLat = pickupLat;
	}

	public String getPickupLng() {
		return pickupLng;
	}

	public void setPickupLng(String pickupLng) {
		this.pickupLng = pickupLng;
	}

	public String getDestinationLat() {
		return destinationLat;
	}

	public void setDestinationLat(String destinationLat) {
		this.destinationLat = destinationLat;
	}

	public String getDestinationLng() {
		return destinationLng;
	}

	public void setDestinationLng(String destinationLng) {
		this.destinationLng = destinationLng;
	}

	public Double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(Double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	
	
}
