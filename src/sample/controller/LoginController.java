package sample.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.domain.User;
import sample.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class); 

	@Autowired
	UserService userService;
	
	@RequestMapping(value="login/login", method=RequestMethod.GET)
	public void login() {
		logger.info("* Welcome login!!");
	}
	
	@RequestMapping(value="login/loginProcess", method=RequestMethod.GET)
	public void loginProcess() {
		logger.info("* Welcome loginProcess!!");
	}
	
	@RequestMapping(value="login/join", method=RequestMethod.GET)
	public void join() {
		logger.info("* Welcome join!!");
	}
	
	@RequestMapping(value="login/joinProcess", method=RequestMethod.POST)
	public String joinProcess(@ModelAttribute User user) {
		logger.info("* Welcome joinProcess!!");
		userService.addUser(user);
		return "redirect:/login/login";
	}
	
}
