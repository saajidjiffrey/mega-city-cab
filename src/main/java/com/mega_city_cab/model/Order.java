package com.mega_city_cab.model;

import java.time.LocalDateTime;

public class Order {
	private int orderId;
	private int bookingId;
	private int driverId;
	private int customerId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double distance;
	private double fareAmount;
	
	//Full constructor
	public Order(int orderId, LocalDateTime startTime,
			LocalDateTime endTime, double distance, double fareAmount,  int bookingId, int driverId, int customerId) {
		this.orderId = orderId;
		this.bookingId = bookingId;
		this.driverId = driverId;
		this.customerId = customerId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.distance = distance;
		this.fareAmount = fareAmount;
	}
	
	public Order(int bookingId, int driverId, int customerId, LocalDateTime startTime,
			LocalDateTime endTime, double distance, double fareAmount) {
		this.bookingId = bookingId;
		this.driverId = driverId;
		this.customerId = customerId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.distance = distance;
		this.fareAmount = fareAmount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getFareAmount() {
		return fareAmount;
	}

	public void setFareAmount(double fareAmount) {
		this.fareAmount = fareAmount;
	}
	
	
	
}
