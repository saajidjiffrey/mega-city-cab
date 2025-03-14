
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.service.CustomerService;

public class CustomerTest {
	private String name = "Saajid Jiffrey Customer";
	private String address = "9/C Aladeniya road, Muruthalawa";
	private String NIC = "200331501101";
	private String phone = "0774737148";
	private String email = "mohamedsaajidcustomer@gmail.com";
	private String userName = "saajidcustomer";
	private String password = "password";
	private String role = "CUSTOMER";
	
	private CustomerService  customerService;
	ObjectMapper objectMapper = new ObjectMapper();
    
	@Before
	public void setUp() throws ServletException {
		customerService = CustomerService.getInstance();	
    }

	@Test
	public void registerCustomer() throws JsonProcessingException, Exception {
		try {
			Customer customer = new Customer();
	        customer.setName(name);
	        customer.setAddress(address);
	        customer.setNIC(NIC);
	        customer.setPhone(phone);
	        customer.setEmail(email);
	        customer.setUserName(userName);
	        customer.setPassword(password);
	        customer.setRole(role);
	        
	        Customer createdCustomer = customerService.registerCustomer(customer);
	        
	        String json = objectMapper.writeValueAsString(createdCustomer);
	        System.out.println(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
    }
}
