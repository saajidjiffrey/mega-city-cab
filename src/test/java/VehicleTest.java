import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mega_city_cab.model.Vehicle;
import com.mega_city_cab.service.VehicleService;

public class VehicleTest {
	private VehicleService  vehicleService;
	ObjectMapper objectMapper;
    
	@Before
	public void setUp() throws ServletException {
		vehicleService = VehicleService.getInstance();	
		objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }
	
	@Test
	public void getAllVehicles () throws SQLException, JsonProcessingException{
		List<Vehicle> vehicles = new ArrayList<>();
        
        try {
        	vehicles = vehicleService.getAllVehicles();
    		
    		String json = objectMapper.writeValueAsString(vehicles);
            System.out.println(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
