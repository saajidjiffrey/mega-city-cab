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
import com.mega_city_cab.model.Vehicle;
import com.mega_city_cab.service.BookingService;
import com.mega_city_cab.service.VehicleService;

/**
 * Servlet implementation class DriverController
 */
@WebServlet("/driver")
public class DriverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingService bookingService;   
	ObjectMapper objectMapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
		bookingService = BookingService.getInstance();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
        if (action.equals("showDriverDashboard")) {
        	showDriverDashboard(request, response);
        } else if (action.equals("showDriverAvailableRides")) {
        	showDriverAvailableRides(request, response);
        } else if (action.equals("showDriverMyRides")) {
        	showDriverMyRides(request, response);
        } else if (action.equals("showDriverOrders")) {
        	showDriverOrders(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
        if (action.equals("acceptBooking")) {
        	driverAcceptBooking(request, response);
        } 
	}

	private void showDriverDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
	    Integer driverId = (Integer) request.getSession().getAttribute("driverId");
	    if (driverId == null) {
	        request.setAttribute("bookingFetchErrorMessage", "Driver is not logged in.");
	        request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	        return;
	    }

	    try {
	        List<Booking> bookingList = bookingService.getAllBookingsByStatus("booked");
	        request.setAttribute("bookings", bookingList);

	        List<Booking> acceptedBookingList = bookingService.getAllBookingsByUserAndStatus("DRIVER", driverId, "accepted");
	        request.setAttribute("acceptedBookingList", acceptedBookingList);
	        
	        List<Booking> completedBookingList = bookingService.getAllBookingsByUserAndStatus("DRIVER", driverId, "completed");
	        request.setAttribute("completedBookingList", completedBookingList);


	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        request.setAttribute("bookingFetchErrorMessage", e.getMessage());
	        request.getRequestDispatcher("pages/driverDashboard.jsp").forward(request, response);
	        return;
	    }

	    request.getRequestDispatcher("pages/driverDashboard.jsp").forward(request, response);
	}

	
	private void showDriverAvailableRides(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllBookingsByStatus("booked");
	        request.setAttribute("bookings", bookingList);

		} catch (Exception e) {
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/driverDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/driverAvailableRides.jsp").forward(request, response);
    }
	
	private void showDriverMyRides(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllBookingsByStatus("accepted", "onride");
	        request.setAttribute("bookings", bookingList);

		} catch (Exception e) {
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/driverDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/driverMyRides.jsp").forward(request, response);
    }
	
	private void showDriverOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllBookingsByStatus("completed");
	        request.setAttribute("bookings", bookingList);

		} catch (Exception e) {
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/driverDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/driverOrders.jsp").forward(request, response);
    }
	
	private void driverAcceptBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("driverId") == null) {
	            throw new Exception("Driver not logged in.");
	        }

	        int driverId = (int) session.getAttribute("driverId");
	        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

	        bookingService.driverAcceptBooking(bookingId, driverId);

	        String jsonResponse = "{\"success\": true, \"message\": \"Booking accepted successfully!\"}";
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
