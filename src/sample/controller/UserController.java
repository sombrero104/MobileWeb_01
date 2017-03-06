package sample.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.domain.User;
import sample.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(BoardController.class); 

	@Autowired
	UserService userService;
	
	@RequestMapping(value="user/mypage", method=RequestMethod.GET)
	public void mypage() {
		logger.info("* Welcome mypage!!");
	}
	
	@RequestMapping(value="user/deleteAccount", method=RequestMethod.GET)
	public void deleteAccount(Authentication authentication) {
		logger.info("* Welcome deleteAccount!!");
		
		User user = (User)authentication.getPrincipal();
		userService.deleteUser(user);
	}
	
	@RequestMapping(value="user/logout", method=RequestMethod.GET)
	public void logout() {
		logger.info("* Welcome logout!!");
	}
	
}
