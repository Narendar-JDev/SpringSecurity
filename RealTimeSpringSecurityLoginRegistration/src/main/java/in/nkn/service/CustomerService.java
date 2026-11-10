package in.nkn.service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nkn.entity.Customer;
import in.nkn.repo.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	public boolean saveCustomer(Customer c) {
		
		// Encrypted the password before saving to the database 
		String encodedpwd = pwdEncoder.encode(c.getPwd());
		c.setPwd(encodedpwd);
		
		//Code to save the record to the database
		Customer saveCustomer = customerRepo.save(c);
	
		return saveCustomer.getId() != null;
	}

	
	//When you perform the login spring security user below logic to perform the login
	// Authentication provider will call this method
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		Customer c = customerRepo.findByEmail(email);
		
		return new User(c.getEmail(), c.getPwd(), Collections.emptyList());
	}
	
	
}
