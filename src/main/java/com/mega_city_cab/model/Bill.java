package com.mega_city_cab.model;

public class Bill {
	private int billId;
	private int orderId;
	private double totalAmount;
	private double tax;
	private double discount;
	private double finalAmount;
	private String paymentStatus;
	
	public Bill(int billId,  double totalAmount, double tax, double discount, double finalAmount,
			String paymentStatus, int orderId) {
		this.billId = billId;
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.tax = tax;
		this.discount = discount;
		this.finalAmount = finalAmount;
		this.paymentStatus = paymentStatus;
	}
	
	public Bill(double totalAmount, double tax, double discount, double finalAmount,
			String paymentStatus, int orderId) {
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.tax = tax;
		this.discount = discount;
		this.finalAmount = finalAmount;
		this.paymentStatus = paymentStatus;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}
