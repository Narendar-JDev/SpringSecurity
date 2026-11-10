package in.nkn.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nkn.entity.Customer;
import in.nkn.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private AuthenticationManager authManager;
	

	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer c){
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPwd());
		
		Authentication authenticate = authManager.authenticate(token);
		
		boolean status = authenticate.isAuthenticated();
		
		if (status) {
			return new ResponseEntity<String>("Login Sucessfully", HttpStatus.OK);
		} else {
            return new ResponseEntity<String>("Please enter correct username or password",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer c){
		
		boolean status = customerservice.saveCustomer(c);
		
         if (status) {
			return new ResponseEntity<>("Register Sucessfully", HttpStatus.CREATED);
		} else {
             return new ResponseEntity<>("Faild to register", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
