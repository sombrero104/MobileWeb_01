package sample.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sample.domain.User;

public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		logger.info("* CustomUserDetailsService loadUserByUsername() username: " + username); // test
		
		User user = userService.findUserById(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."); 
		}
		
		return user;
	}
	
}
