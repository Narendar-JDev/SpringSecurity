package in.nkn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AppSecurityConfigurer {
	/*
	*
	*This is the code using this we will decide on which endpoint we need to apply the security 
	*
	*/
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {

	    http
	        .authorizeHttpRequests(req -> req
	            .requestMatchers("/api/security/aboutus").permitAll()
	            .anyRequest().authenticated()
	        )
	        .httpBasic(Customizer.withDefaults())
	        .formLogin(form -> form.disable());

	    return http.build();
	}
	
	/*
	*
	*This is the code using this we need apply inMemory security so this need for e.g
	*if our rest api used by 3 company in such scnario we need give the diff diff username
	*and password 
	*
	*/
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUsers() {
		
		UserDetails u1 = User.withDefaultPasswordEncoder().
				              username("narendar").
				              password("narendar@gmail").
				              build();
		
		UserDetails u2 = User.withDefaultPasswordEncoder().
	              username("vivek").
	              password("vivek@gmail.com").
	              build();
		
		UserDetails u3 = User.withDefaultPasswordEncoder().
	              username("shrikant").
	              password("shrikant@gmail.com").
	              build();
		
		UserDetails u4 = User.withDefaultPasswordEncoder().
	              username("vinay").
	              password("vinay@gmail.com").
	              build();
		
		
		return new InMemoryUserDetailsManager(u1,u2,u3,u4);
	}
	
	


}
