package in.nkn.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import in.nkn.service.CustomerService;
import in.nkn.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AppFilter extends OncePerRequestFilter {
	


	    @Autowired
	    private JwtService jwtService;
	    
	    @Autowired
	    CustomerService userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = null;
	    String email = null;
		
		//Code to find the complete token
		String header = request.getHeader("Authorization");
		
	    //Check if the header statrt with bearer space
		if(header != null && header.startsWith("Bearer ")) {
			
			token = header.substring(7);
			jwtService.extractUsername(token);
		}
		
		 if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
	            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
	            if(jwtService.validateToken(token, userDetails)){
	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	

}
}
