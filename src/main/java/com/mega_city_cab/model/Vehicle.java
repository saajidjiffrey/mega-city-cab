package com.mega_city_cab.model;

public class Vehicle {
	private int vehicleId;
	private int passengerCount;
	private double pricePerKm;
	private String vehicleType;
	private double taxPercentage;
	private double discountPercentage;
	
	public Vehicle(int vehicleId, int passengerCount, double pricePerKm, String vehicleType, double taxPercentage, double discountPercentage) {
		super();
		this.vehicleId = vehicleId;
		this.passengerCount = passengerCount;
		this.pricePerKm = pricePerKm;
		this.vehicleType = vehicleType;
		this.taxPercentage = taxPercentage;
		this.discountPercentage = discountPercentage;
	}
	
	public Vehicle(int passengerCount, double pricePerKm, String vehicleType, double taxPercentage, double discountPercentage) {
		super();
		this.passengerCount = passengerCount;
		this.pricePerKm = pricePerKm;
		this.vehicleType = vehicleType;
		this.taxPercentage = taxPercentage;
		this.discountPercentage = discountPercentage;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	public double getPricePerKm() {
		return pricePerKm;
	}

	public void setPricePerKm(double pricePerKm) {
		this.pricePerKm = pricePerKm;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
}
