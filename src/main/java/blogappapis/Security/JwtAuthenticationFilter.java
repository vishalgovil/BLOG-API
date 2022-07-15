package blogappapis.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. get token
		
		String requestToken=request.getHeader("Authorization");
		
		// Bearer 
		System.out.println(requestToken);
		
		String username=null;
		
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
		 token = requestToken.substring(7);
		 try {
		 username=  this.jwtTokenHelper.getUsernameFromToken(token);
		 }
		 catch(IllegalArgumentException e)
		 {
			 System.out.println("Unable to get Jwt Token");
		 }
		 catch(ExpiredJwtException e)
		 {
			 System.out.println("Jwt token has expired");
		 }
		 catch(MalformedJwtException e)
		 {
			 System.out.println("invalid jwt");
		 }
		 
		}
		else
		{
			System.out.println("JWT does not begin with Bearer");
		}
		
		// validate
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{	
			UserDetails userDetails= userDetailsService.loadUserByUsername(username);
			if(this.jwtTokenHelper.validateToken(token,userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken); 
			}
			else
			{
				System.out.println("Invalid JWT Token");
			}
		}
		else
		{
			System.out.println("User name is null or context is null");
		}
		
		filterChain.doFilter(request, response);
	}

}
