package sample.support;

import java.util.Collection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import sample.service.CustomUserDetailsService;
 
public class CustomAuthenticationProvider implements AuthenticationProvider { 
    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {         
    	String username = authentication.getName(); // Input login form
        String password = (String)authentication.getCredentials(); // Input login form
        
        UserDetails userDetails;
        Collection<? extends GrantedAuthority> authorities; 
        try { 
        	userDetails = customUserDetailsService.loadUserByUsername(username); // DB inform
        	String hashedPassword = passwordEncoder.encode(password);
        	
        	logger.info("* CustomAuthenticationProvider authenticate() (1) Input login form: " + username + " / " + password + " / " + hashedPassword); 
        	logger.info("* CustomAuthenticationProvider authenticate() (2) DB inform: " + userDetails.getUsername() + " / " + userDetails.getPassword()); 
        	
        	if (!passwordEncoder.matches(password, userDetails.getPassword())) { 
        		throw new BadCredentialsException( "암호가 일치하지 않습니다." );
        	} else {
        		logger.info("암호 일치!!"); // test
        	}
        	
        	authorities = userDetails.getAuthorities(); 
        	logger.info("* authorities: " + authorities); // test
        	
    	} catch(UsernameNotFoundException e) { 
    		logger.info(e.toString()); 
    		throw new UsernameNotFoundException(e.getMessage()); 
    	} catch(BadCredentialsException e) { 
    		logger.info(e.toString()); 
    		throw new BadCredentialsException(e.getMessage()); 
    	} catch(Exception e) { 
    		logger.info(e.toString()); 
    		throw new RuntimeException(e.getMessage()); 
    	}
        
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }
    
}
