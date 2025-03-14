package com.mega_city_cab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mega_city_cab.model.Order;
import com.mega_city_cab.model.Vehicle;
import com.mega_city_cab.service.BookingService;
import com.mega_city_cab.service.OrderService;
import com.mega_city_cab.service.VehicleService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService;   
	private BookingService bookingService;   
	private VehicleService vehicleService;   
	ObjectMapper objectMapper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	orderService = OrderService.getInstance();
    	bookingService = BookingService.getInstance();
    	vehicleService = VehicleService.getInstance();
		objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("createOrder")) {
			createOrder(request, response);
        } 
	}
	


	private void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    System.out.println("order");
	    try {
	        HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("driverId") == null) {
	            throw new Exception("Driver not logged in.");
	        }

	        int driverId = (int) session.getAttribute("driverId");
	        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
	        double distance = Double.parseDouble(request.getParameter("distance"));
	        String startTimeStr = request.getParameter("startTime");
	        String endTimeStr = request.getParameter("endTime");
	        int customerId = Integer.parseInt(request.getParameter("customerId"));
	        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

	        if (startTimeStr == null || endTimeStr == null) {
	            throw new Exception("Start time and end time are required.");
	        }

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
	        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
	        
	        //get vehicle 
	        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
	        
	    

	        // Create the order
	        Order order = new Order();
	        order.setBookingId(bookingId);
	        order.setDriverId(driverId);
	        order.setDistance(distance);
	        order.setFareAmount(vehicle.getPricePerKm()*distance); 
	        order.setStartTime(startTime);
	        order.setEndTime(endTime);
	        order.setCustomerId(customerId);

	        Order createdOrder = orderService.createOrder(order);

	        if (createdOrder != null) {
	            boolean statusUpdated = bookingService.updateBookingStatus(bookingId, "completed");

	            if (statusUpdated) {
	                String jsonResponse = "{\"success\": true, \"message\": \"Order created and booking status updated to completed!\"}";
	                response.getWriter().write(jsonResponse);
	            } else {
	                throw new Exception("Failed to update booking status.");
	            }
	        } else {
	            throw new Exception("Failed to create order.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();

	        String errorResponse = "{\"success\": false, \"message\": \"" + e.getMessage() + "\"}";
	        response.getWriter().write(errorResponse);
	    } finally {
	        response.getWriter().flush();
	        response.getWriter().close();
	    }
	}




}
