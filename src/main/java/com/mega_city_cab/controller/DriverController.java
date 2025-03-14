package com.mega_city_cab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		doGet(request, response);
	}

	private void showDriverDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
//		try {
//			List<Vehicle> vehicleList = vehicleService.getAllVehicles();
//	        request.setAttribute("vehicles", vehicleList);
//	        String json = objectMapper.writeValueAsString(vehicleList);
//            System.out.println(json);
//		} catch (Exception e) {
//			request.setAttribute("vehicleFetchErrorMessage", e.getMessage());
//			request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);
//            return;
//		}
        request.getRequestDispatcher("pages/driverDashboard.jsp").forward(request, response);
    }
	
	private void showDriverAvailableRides(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		try {
			List<Booking> bookingList = bookingService.getAllBookingsByStatus("booked");
	        request.setAttribute("bookings", bookingList);

		} catch (Exception e) {
			request.setAttribute("bookingFetchErrorMessage", e.getMessage());
			request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);
            return;
		}
        request.getRequestDispatcher("pages/driverAvailableRides.jsp").forward(request, response);
    }
	
	private void showDriverMyRides(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        request.getRequestDispatcher("pages/driverMyRides.jsp").forward(request, response);
    }
	
	private void showDriverOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        request.getRequestDispatcher("pages/driverOrders.jsp").forward(request, response);
    }
}
