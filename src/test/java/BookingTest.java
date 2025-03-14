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

	private BookingService bookingService;
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws ServletException {
		bookingService = BookingService.getInstance();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void createBooking() throws JsonProcessingException {
		String pickupLocation = "Kandy";
		String destination = "Colombo";
		LocalDateTime bookingDatetime = LocalDateTime.of(2025, 3, 1, 10, 30);
		int customerId = 378;

		String pickupLat = "8.2250";
		String pickupLng = "9.50";
		String destinationLat = "7.2250";
		String destinationLng = "6.2250";
		double estimatedTime = 5.2;
		double distance = 45.20;
		int vehicleId = 1;
		
		try {
			Booking createdBooking = bookingService.createBooking(pickupLocation, destination,bookingDatetime, customerId, pickupLat, pickupLng, destinationLat, destinationLng, estimatedTime, distance, vehicleId);
			String json = objectMapper.writeValueAsString(createdBooking);
			System.out.println(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void updateBookingStatus() throws JsonProcessingException {
		int bookingId = 869;
		String status = "accepted";
		try {
			System.out.println(bookingService.updateBookingStatus(bookingId, status));
		} catch (Exception e) {
			System.out.println( e.getMessage());
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
	public void getAllBookings() throws SQLException, JsonProcessingException {
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
	public void getAllBookingsByCustomer() throws SQLException, JsonProcessingException {
		List<Booking> bookings = new ArrayList<>();
		int customerId = 37;

		try {
			bookings = bookingService.getAllBookingsByCustomer(customerId);

			String json = objectMapper.writeValueAsString(bookings);
			System.out.println(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void getAllBookingsByDriver() throws SQLException, JsonProcessingException {
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
	public void getAllBookingsByBookingStatus() throws SQLException, JsonProcessingException {
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
	public void getAllBookingsByBookingStatuses() throws SQLException, JsonProcessingException {
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
	public void getBookingById() throws SQLException, JsonProcessingException {
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
