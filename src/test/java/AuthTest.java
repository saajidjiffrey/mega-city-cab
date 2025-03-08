import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mega_city_cab.exception.AuthenticationException;
import com.mega_city_cab.model.User;
import com.mega_city_cab.service.AuthenticationService;

public class AuthTest {
	private AuthenticationService authService;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void setUp() throws ServletException {
		authService = AuthenticationService.getInstance();	
    }

	@Test
	public void userLogin() throws JsonProcessingException{
        String userName = "saajid146";
        String password = "Saajid1234!";
        
		try {
			User loggedUser = authService.userLogin(userName, password);
			String json = objectMapper.writeValueAsString(loggedUser);
	        System.out.println(json);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
