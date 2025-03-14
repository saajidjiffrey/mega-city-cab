
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mega_city_cab.model.Admin;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.service.AdminService;
import com.mega_city_cab.service.CustomerService;

public class AdminTest {
	private String name = "Saajid Jiffrey Admin";
	private String address = "9/C Aladeniya road, Muruthalawa";
	private String NIC = "123456789123";
	private String phone = "0774737345";
	private String email = "mohamedsaajidadmin@gmail.com";
	private String userName = "saajidAdmin";
	private String password = "password";
	private String role = "ADMIN";
	
	private AdminService  adminService;
	ObjectMapper objectMapper = new ObjectMapper();
    
	@Before
	public void setUp() throws ServletException {
		adminService = AdminService.getInstance();	
    }

	@Test
	public void registerAdmin() throws JsonProcessingException, Exception {
		try {
			Admin admin = new Admin();
			admin.setName(name);
	        admin.setAddress(address);
	        admin.setNIC(NIC);
	        admin.setPhone(phone);
	        admin.setEmail(email);
	        admin.setUserName(userName);
	        admin.setPassword(password);
	        admin.setRole(role);
	        
			Admin createdAdmin = adminService.registerAdmin(admin);
	        
	        String json = objectMapper.writeValueAsString(createdAdmin);
	        System.out.println(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
    }
}
