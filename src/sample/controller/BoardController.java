package sample.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.domain.User;

@Controller
public class BoardController {
	private static final Logger logger = Logger.getLogger(BoardController.class); 

	@RequestMapping(value="board/board", method=RequestMethod.GET)
	public void board(Authentication authentication) {
		logger.info("* Welcome board!!");
		User user = (User)authentication.getPrincipal();
		logger.info("* BoardController board() user.getUsername(): " + user.getUsername());
	}
	
}
