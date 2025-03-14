package com.mega_city_cab.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Vehicle;
import com.mega_city_cab.service.BookingService;
import com.mega_city_cab.service.VehicleService;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/customer")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;   
	private BookingService bookingService;   
	ObjectMapper objectMapper;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public void init() throws ServletException {
		vehicleService = VehicleService.getInstance();
		bookingService = BookingService.getInstance();
		objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
	
    public CustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(false); 
//	    String userType = (String) session.getAttribute("userType");
//
//	    if (userType == null || !userType.equals("CUSTOMER")) {
//	        response.sendRedirect("pages/login.jsp?error=Unauthorized access");
//	        return;
//	    }
	    
		String action = request.getParameter("action");
        if (action.equals("showCustomerDashboard")) {
        	showCustomerDashboard(request, response);
        } else if (action.equals("showCustomerBookings")) {
        	showCustomerBookings(request, response);
        }  else if (action.equals("showCustomerOrders")) {
        	showCustomerOrders(request, response);
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        if (action.equals("createBooking")) {
        	createBooking(request, response);
        } else if (action.equals("deleteBooking")) {
        	deleteBooking(request, response);
        }
	}
	
	private void showCustomerDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Vehicle> vehicleList = vehicleService.getAllVehicles();
	        request.setAttribute("vehicles", vehicleList);
	        String json = objectMapper.writeValueAsString(vehicleList);
            System.out.println(json);
		} catch (Exception e) {
			request.setAttribute("vehicleFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);
    }
	
	private void createBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        String pickupLocation = request.getParameter("pickupLocation");
	        String destination = request.getParameter("destination");
	        String bookingDatetimeStr = request.getParameter("bookingDatetime");
	        int customerId = (int) session.getAttribute("customerId");
	        String pickupLat = request.getParameter("pickupLat");
	        String pickupLng = request.getParameter("pickupLng");
	        String destinationLat = request.getParameter("destinationLat");
	        String destinationLng = request.getParameter("destinationLng");
	        String estimatedTimeStr = request.getParameter("estimatedTime");
	        String distanceStr = request.getParameter("distance");
	        String vehicleIdStr = request.getParameter("vehicleType");

	        LocalDateTime bookingDatetime = (bookingDatetimeStr != null && !bookingDatetimeStr.isEmpty()) 
	            ? LocalDateTime.parse(bookingDatetimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) 
	            : null;

	        double estimatedTime = (estimatedTimeStr != null && !estimatedTimeStr.isEmpty()) ? Double.parseDouble(estimatedTimeStr) : 0.0;
	        double distance = (distanceStr != null && !distanceStr.isEmpty()) ? Double.parseDouble(distanceStr) : 0.0;
	        int vehicleId = (vehicleIdStr != null && !vehicleIdStr.isEmpty()) ? Integer.parseInt(vehicleIdStr) : 0;

	        Booking createdBooking = bookingService.createBooking(pickupLocation, destination, bookingDatetime, customerId, 
	            pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId);

	        String json = objectMapper.writeValueAsString(createdBooking);
	        System.out.println("Created Booking JSON: " + json);

	        String jsonResponse = "{\"success\": true, \"message\": \"Booking Success! Go to My Bookings to check status.\"}";
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().write(jsonResponse);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error during booking: " + e.getMessage());

	        String errorResponse = "{\"success\": false, \"message\": \"Booking failed. Please try again!\"}";
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write(errorResponse);
	    } finally {
	        response.getWriter().flush();
	        response.getWriter().close();
	    }
	}

	
	private void showCustomerOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllCompletedBookings();
	        request.setAttribute("completedBookings", bookingList);
	        String json = objectMapper.writeValueAsString(bookingList);
            System.out.println(json);
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/customerOrders.jsp").forward(request, response);
    }
	
	private void showCustomerBookings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllBookings();
	        request.setAttribute("bookings", bookingList);
	        String json = objectMapper.writeValueAsString(bookingList);
            System.out.println(json);
		} catch (Exception e) {
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/customerBookings.jsp").forward(request, response);
    }
	
	private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {	    	
	    	int bookingId = Integer.parseInt(request.getParameter("bookingId"));

	        bookingService.deleteBooking(bookingId);

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        String jsonResponse = "{\"success\": true, \"message\": \"Booking deleted successfully!\"}";
	        response.getWriter().write(jsonResponse);
	    
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	e.printStackTrace();
	    	
	    	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        String errorResponse = "{\"success\": false, \"message\": \"Failed to delete booking. Please try again!\"}";
	        response.getWriter().write(errorResponse);
	    } 
	}

}
