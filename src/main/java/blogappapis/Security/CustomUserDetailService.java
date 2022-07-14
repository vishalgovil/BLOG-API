package blogappapis.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import blogappapis.entities.User;
import blogappapis.exception.ResourceNotFoundException;
import blogappapis.repositories.UserRepo;

public class CustomUserDetailService  implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
		User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User ", "User Email "+username, 0));
		return user;
	}
	

}
