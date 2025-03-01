
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mega_city_cab.model.Customer;
import com.mega_city_cab.service.CustomerService;

public class CustomerTest {
	private String name = "Saajid Jiffrey";
	private String address = "9/C Aladeniya road, Muruthalawa";
	private String NIC = "200231501106";
	private String phone = "0774737149";
	private String email = "mohamedsaajid5@gmail.com";
	private String userName = "saajid146";
	private String password = "Saajid1234!";
	
	private CustomerService  customerService;
	ObjectMapper objectMapper = new ObjectMapper();
    
	@Before
	public void setUp() throws ServletException {
		customerService = CustomerService.getInstance();	
    }

	@Test
	public void registerCustomer() throws JsonProcessingException {
        Customer customer = new Customer(name, address, NIC, phone, email, userName, password);
        Customer createdCustomer = customerService.registerCustomer(customer);
        
        String json = objectMapper.writeValueAsString(createdCustomer);
        System.out.println(json);
    }
}
