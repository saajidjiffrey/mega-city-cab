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
	private String userName = "saajidDriver";
	private String password = "password";
	private String licenseNo = "123456abcdef";
	private String role = "DRIVER";
	
	private DriverService driverService;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void setUp() throws ServletException {
		driverService = DriverService.getInstance();	
    }

	@Test
	public void registerDriver() throws JsonProcessingException, Exception{       
		try {
	        Driver driver = new Driver();
	        driver.setName(name);
	        driver.setAddress(address);
	        driver.setNIC(NIC);
	        driver.setPhone(phone);
	        driver.setEmail(email);
	        driver.setUserName(userName);
	        driver.setPassword(password);
	        driver.setLicenseNo(licenseNo);
	        driver.setRole(role);
	        
	        Driver createdDriver = driverService.registerDriver(driver);
	        
	        String json = objectMapper.writeValueAsString(createdDriver);
	        System.out.println(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }

}
