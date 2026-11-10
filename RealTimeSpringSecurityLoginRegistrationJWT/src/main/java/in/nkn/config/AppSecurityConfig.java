package in.nkn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.nkn.service.CustomerService;
import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {


	
	@Autowired
	private CustomerService customerService;


	
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {

	    DaoAuthenticationProvider authProvider =
	            new DaoAuthenticationProvider(customerService);

	    authProvider.setPasswordEncoder(pwdEncoder());

	    return authProvider;
	}
	
	// AuthenticationManager will check login is valid or not
	@Bean
	@SneakyThrows
	public AuthenticationManager authManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http) {

        http.authorizeHttpRequests((req) ->{
        	req.requestMatchers("/api/register","/api/login")
        	.permitAll()
        	.anyRequest()
        	.authenticated();
        }); 
		
        http.csrf(csrf -> csrf.disable());
        return http.build();
	}
}
