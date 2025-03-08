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
	import com.mega_city_cab.service.BookingService;
	
	public class BookingTest {
		private String pickupLocation = "Kandy";
		private String destination = "Colombo";
		private LocalDateTime bookingDatetime = LocalDateTime.of(2025, 3, 1, 10, 30); 
		private int customerId = 11;
		
		private BookingService  bookingService;
		ObjectMapper objectMapper;
	    
		@Before
		public void setUp() throws ServletException {
			bookingService = BookingService.getInstance();	
			objectMapper = new ObjectMapper();
	        objectMapper.registerModule(new JavaTimeModule());
	    }
	
		@Test
		public void createBooking() throws JsonProcessingException {
			try {
		        Booking createdBooking = bookingService.createBooking(pickupLocation, destination, bookingDatetime, customerId);
		        String json = objectMapper.writeValueAsString(createdBooking);
		        System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	    }
		
		@Test
		public void updateBookingStatus() throws JsonProcessingException {
			int bookingId = 3;
			String status = "completed";
			try {
				System.out.println(bookingService.updateBookingStatus(bookingId, status));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	    }
		
		@Test
		public void deleteBooking() throws JsonProcessingException {
			int bookingId = 5;
			try {
		        bookingService.deleteBooking(bookingId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	    }
		
		@Test
		public void getAllBookings () throws SQLException, JsonProcessingException{
			List<Booking> bookings = new ArrayList<>();
	        
	        try {
	        	bookings = bookingService.getAllBookings();
	    		
	    		String json = objectMapper.writeValueAsString(bookings);
	            System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void getAllBookingsByCustomer () throws SQLException, JsonProcessingException{
			List<Booking> bookings = new ArrayList<>();
	        int customerId = 11;
			
	        try {
	        	bookings = bookingService.getAllBookingsByCustomer(customerId);
	    		
	    		String json = objectMapper.writeValueAsString(bookings);
	            System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void getAllBookingsByDriver () throws SQLException, JsonProcessingException{
			List<Booking> bookings = new ArrayList<>();
	        int driverId = 1;
			
	        try {
	        	bookings = bookingService.getAllBookingsByDriver(driverId);
	    		
	    		String json = objectMapper.writeValueAsString(bookings);
	            System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void getAllBookingsByBookingStatus () throws SQLException, JsonProcessingException{
			List<Booking> bookings = new ArrayList<>();
	        String status = "accepted";
			
	        try {
	        	bookings = bookingService.getAllBookingsByStatus(status);
	    		
	    		String json = objectMapper.writeValueAsString(bookings);
	            System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void getAllBookingsByBookingStatuses () throws SQLException, JsonProcessingException{
			List<Booking> bookings = new ArrayList<>();
	        String status1 = "accepted";
	        String status2 = "started";
			
	        try {
	        	bookings = bookingService.getAllBookingsByStatus(status1, status2);
	    		
	    		String json = objectMapper.writeValueAsString(bookings);
	            System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void getBookingById() throws SQLException, JsonProcessingException{
	        int bookingId = 9;
			
	        try {
	        	Booking booking = bookingService.getBookingById(bookingId);
	    		
	    		String json = objectMapper.writeValueAsString(booking);
	            System.out.println(json);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
