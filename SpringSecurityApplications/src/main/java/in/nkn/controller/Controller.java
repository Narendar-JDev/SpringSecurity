package in.nkn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security/")
public class Controller {
	
	@GetMapping("/login")
	public String login() {
		return "Login Sucessfully";
	}
	
	@GetMapping("/transfer")
	public String transfer() {
		return "Money transfer sucessfully";
	}
	
	@GetMapping("/aboutus")
	public String aboutus() {
		return "This is about us page";
	}
	
	@GetMapping("/checkbalence")
	public String contactus() {
		return "This is contact us page";
	}

}
