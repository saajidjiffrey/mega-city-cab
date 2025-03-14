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
import com.mega_city_cab.service.BookingService;
import com.mega_city_cab.service.VehicleService;

/**
 * Servlet implementation class BookingController
 */
@WebServlet("/booking")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingService bookingService;   
	ObjectMapper objectMapper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public void init() throws ServletException {
		bookingService = BookingService.getInstance();
		objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
	
    public BookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("deleteBooking")) {
        	deleteBooking(request, response);
        } else if (action.equals("updateBookingStatus")) {
        	updateBookingStatus(request, response);
        } 
		
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


	
	private void updateBookingStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("driverId") == null) {
	            throw new Exception("Driver not logged in.");
	        }

	        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
	        String status = request.getParameter("status");

	        if (status == null || status.trim().isEmpty()) {
	            throw new Exception("Invalid status provided.");
	        }

	        boolean isUpdated = bookingService.updateBookingStatus(bookingId, status);

	        if (isUpdated) {
	            String jsonResponse = "{\"success\": true, \"message\": \"Booking status updated successfully!\"}";
	            response.getWriter().write(jsonResponse);
	        } else {
	            throw new Exception("Failed to update booking status.");
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
