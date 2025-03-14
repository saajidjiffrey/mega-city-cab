package com.mega_city_cab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Driver;
import com.mega_city_cab.service.BookingService;
import com.mega_city_cab.service.DriverService;
import com.mega_city_cab.service.VehicleService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingService bookingService;   
	private DriverService driverService;   
	ObjectMapper objectMapper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
		bookingService = BookingService.getInstance();
		driverService = DriverService.getInstance();
		objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        if (action.equals("showAdminDashboard")) {
        	showCustomerDashboard(request, response);
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        if (action.equals("assignBookingToDriver")) {
        	assignBookingToDriver(request, response);
        }
	}

	private void showCustomerDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllBookings();
	        request.setAttribute("bookings", bookingList);
	        
	        List<Driver> driverList = driverService.getAllDrivers();
	        request.setAttribute("drivers", driverList); 

		} catch (Exception e) {
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/adminDashboard.jsp").forward(request, response);
    }
	
	private void assignBookingToDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		 response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");

		    try {

		        int driverId = Integer.parseInt(request.getParameter("driverId"));
		        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

		        boolean isAssigned = bookingService.assignBookingToDriver(bookingId, driverId);

		        String jsonResponse;
		        if (isAssigned) {
		            jsonResponse = "{\"success\": true, \"message\": \"Booking assigned to driver successfully!\"}";
		        } else {
		            jsonResponse = "{\"success\": false, \"message\": \"Failed to assign booking. Please try again.\"}";
		        }
		        
		        response.getWriter().write(jsonResponse);

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
