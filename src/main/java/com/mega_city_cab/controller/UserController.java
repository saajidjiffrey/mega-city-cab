package com.mega_city_cab.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mega_city_cab.exception.AuthenticationException;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Driver;
import com.mega_city_cab.model.User;
import com.mega_city_cab.service.AuthenticationService;
import com.mega_city_cab.service.CustomerService;
import com.mega_city_cab.service.DriverService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
	private DriverService driverService;
	private AuthenticationService authenticationService;
	ObjectMapper objectMapper = new ObjectMapper();
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public void init() throws ServletException {
		customerService = CustomerService.getInstance();
		driverService = DriverService.getInstance();
		authenticationService = AuthenticationService.getInstance();
		
    }
	
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
        if (action.equals("signup")) {
        	showSignupPage(request, response);
        } else if (action.equals("login")) {
        	showLoginPage(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        if (action.equals("customerSignup")) {
        	customerSignup(request, response);
        } else if (action.equals("driverSignup")) {
        	driverSignup(request, response);
        } else if (action.equals("userLogin")) {
        	try {
				userLogin(request, response);
			} catch (ServletException | IOException | AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void showSignupPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
    }
	
	private void customerSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    	System.out.println(request.getParameter("cName"));
	        // Get form parameters
	        String name = request.getParameter("cName");
	        String address = request.getParameter("cAddress");
	        String NIC = request.getParameter("cNIC");
	        String phone = request.getParameter("cPhone");
	        String email = request.getParameter("cEmail");
	        String userName = request.getParameter("cUserName");
	        String password = request.getParameter("cPassword");
	        
	        // Create Customer object
	        Customer customer = new Customer();
	        customer.setName(name);
	        customer.setAddress(address);
	        customer.setNIC(NIC);
	        customer.setPhone(phone);
	        customer.setEmail(email);
	        customer.setUserName(userName);
	        customer.setPassword(password);

	        // Register customer
	        Customer createdCustomer = customerService.registerCustomer(customer);
	        
	        // Store user details in session
	        HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", createdCustomer.getCustomerId());
	        session.setAttribute("userType",createdCustomer.getRole());
	        session.setAttribute("customerId", createdCustomer.getCustomerId());

	     // Prepare a JSON response
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        // Send a success response back to the client
	        String jsonResponse = "{\"success\": true, \"message\": \"Signed up successfully!.\"}";
	        response.getWriter().write(jsonResponse);
	        // Redirect if successful
//	        request.getRequestDispatcher("pages/customerDashboard.jsp").forward(request, response);

	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	e.printStackTrace();
	    	
	    	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");	 
	        
	        String errorResponse = "{\"success\": false, \"message\": \"Failes to signup.!\"}";
	        response.getWriter().write(errorResponse);
	    }
	}
	
	private void driverSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    	System.out.println(request.getParameter("dName"));

			String name = request.getParameter("dName");
	        String address = request.getParameter("dAddress");
	        String NIC = request.getParameter("dNIC");
	        String phone = request.getParameter("dPhone");
	        String email = request.getParameter("dEmail");
	        String userName = request.getParameter("dUserName");
	        String password = request.getParameter("dPassword");
	        String licenseNo = request.getParameter("dLicenseNo");
	        
	        Driver driver = new Driver();
	        driver.setName(name);
	        driver.setAddress(address);
	        driver.setNIC(NIC);
	        driver.setPhone(phone);
	        driver.setEmail(email);
	        driver.setUserName(userName);
	        driver.setPassword(password);
	        driver.setLicenseNo(licenseNo);
	        
	        Driver createdDriver= driverService.registerDriver(driver);
	        
	        HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", createdDriver.getDriverId());
	        session.setAttribute("userType",createdDriver.getRole());
	        session.setAttribute("driverId", createdDriver.getDriverId());
	        
	        String json = objectMapper.writeValueAsString(createdDriver);
	        System.out.println(json);
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        String jsonResponse = "{\"success\": true, \"message\": \"Signed up successfully!.\"}";
	        response.getWriter().write(jsonResponse);
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
	    	e.printStackTrace();
	    	
	    	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");	 
	        
	        String errorResponse = "{\"success\": false, \"message\": \"Failed to signup.!\"}";
	        response.getWriter().write(errorResponse);
		}
    }

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/login.jsp").forward(request, response);
    }
	
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AuthenticationException {
	    try {
	    	String userName = request.getParameter("userName");
		    String password = request.getParameter("password");
		    
	        User user = authenticationService.userLogin(userName, password);

	        // Set user details in session
	        HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", user);
	        session.setAttribute("userType", user.getRole());

	        if (user instanceof Customer) {
	            Customer customer = (Customer) user;
	            session.setAttribute("customerId", customer.getCustomerId());
	        } else if (user instanceof Driver) {
	            Driver driver = (Driver) user;
	            session.setAttribute("driverId", driver.getDriverId());
	        }
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	       
	        // Redirect based on role
	        if ("CUSTOMER".equalsIgnoreCase(user.getRole())) {
		        String jsonResponse = "{\"success\": true, \"message\": \"Logged In successfully!.C\"}";
		        response.getWriter().write(jsonResponse);
	        } else if ("DRIVER".equalsIgnoreCase(user.getRole())) {
		        String jsonResponse = "{\"success\": true, \"message\": \"Logged In successfully!.D\"}";
		        response.getWriter().write(jsonResponse);
	        } else {
	            session.invalidate();
	            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	        }


	    } catch (AuthenticationException e) {
	        System.out.println(e.getMessage());
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");	 
	        
	        String errorResponse = "{\"success\": false, \"message\": \"Failes to signup.!\"}";
	        response.getWriter().write(errorResponse);
	        
	        request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	    }
    }
}
