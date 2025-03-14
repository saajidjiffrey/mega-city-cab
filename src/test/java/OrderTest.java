import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mega_city_cab.model.Booking;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Order;
import com.mega_city_cab.model.Vehicle;
import com.mega_city_cab.service.OrderService;
import com.mega_city_cab.service.VehicleService;

public class OrderTest {
	private OrderService  orderService;
	ObjectMapper objectMapper;
    
	@Before
	public void setUp() throws ServletException {
		orderService = OrderService.getInstance();	
		objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
	
	@Test
	public void createOrder() throws JsonProcessingException, Exception {
	    int bookingId = 86;
	    int driverId = 4;
	    int customerId = 347;
	    LocalDateTime startTime = LocalDateTime.of(2025, 3, 1, 10, 30);
	    LocalDateTime endTime = LocalDateTime.of(2025, 3, 1, 11, 30);
	    double distance = 45.2;
	    double fareAmount = 550.0;

	    Order order = new Order();
	    order.setBookingId(bookingId);
	    order.setDriverId(driverId);
	    order.setCustomerId(customerId);
	    order.setStartTime(startTime);
	    order.setEndTime(endTime);
	    order.setDistance(distance);
	    order.setFareAmount(fareAmount);

	    try {
	        Order createdOrder = orderService.createOrder(order);
	        String json = objectMapper.writeValueAsString(createdOrder);
	        System.out.println(json);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}


}
