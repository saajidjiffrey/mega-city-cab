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
import com.mega_city_cab.service.BillService;
import com.mega_city_cab.service.BookingService;

public class BillTest {

	private BillService billService;
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws ServletException {
		billService = BillService.getInstance();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	
	@Test
	public void updateBillPaymentStatus() throws JsonProcessingException {
		int billId = 3;
		String status = "PAID";
		try {
			System.out.println(billService.updateBillPaymentStatus(billId, status));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
