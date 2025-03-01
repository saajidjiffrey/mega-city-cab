import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.model.Driver;
import com.mega_city_cab.service.CustomerService;
import com.mega_city_cab.service.DriverService;

public class DriverTest {
	private String name = "Saajid Jiffrey";
	private String address = "9/C Aladeniya road, Muruthalawa";
	private String NIC = "200131501106";
	private String phone = "0764737149";
	private String email = "mohamedsaajid@gmail.com";
	private String userName = "saajid14";
	private String password = "Saajid123!";
	private String licenseNo = "123456abcdef";
	
	private DriverService driverService;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void setUp() throws ServletException {
		driverService = DriverService.getInstance();	
    }

	@Test
	public void registerDriver() throws JsonProcessingException {
        Driver driver = new Driver(name, address, NIC, phone, email, userName, password, licenseNo);
        Driver createdDriver = driverService.registerDriver(driver);
        
        String json = objectMapper.writeValueAsString(createdDriver);
        System.out.println(json);
    }

}
