package sample.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sample.domain.Goods;
import sample.domain.User;
import sample.service.GoodsService;

@Controller
public class GoodsController {
	private static final Logger logger = Logger.getLogger(GoodsController.class); 

	@Autowired
	GoodsService goodsService;
	
	/**
	 * Mobile web - searchList
	 */
	@RequestMapping(value="goods/searchList")
	public ModelAndView searchList(Authentication authentication
			, @RequestParam(defaultValue="1") int currentPage, @RequestParam(defaultValue="5") int pageSize) {
		ModelAndView modelAndView = new ModelAndView("goods/searchList");
		
		System.out.println("* currentPage: " + currentPage); // test
		
		
		logger.info("* Welcome searchList!!");
		if(authentication != null) {
			User user = (User)authentication.getPrincipal();
			logger.info("* GoodsController searchList() user.getUsername(): " + user.getUsername());
		}
		
		// TODO
		String test = "Test!!";
		
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("pageSize", pageSize);
		modelAndView.addObject("test", test);
		return modelAndView;
	}
	
	/**
	 * Mobile web - goodsDiv
	 */
	@RequestMapping(value="goods/goodsDiv")
	public ModelAndView goodsDiv(Authentication authentication
			, @RequestParam int currentPage, @RequestParam int pageSize) {
		ModelAndView modelAndView = new ModelAndView("goods/goodsDiv");
		logger.info("* Welcome goodsDiv!!");
		
		// TODO
		int startNum = ((currentPage-1)*pageSize);
		int endNum = ((currentPage-1)*pageSize)+pageSize-1;
		endNum = endNum + 1; // because of setMaxResults()
		System.out.println("* startNum: " + startNum + " / endNum: " + endNum); // test
		List<Goods> goodsList = goodsService.getGoodsList(startNum, endNum);
		
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("pageSize", pageSize);
		modelAndView.addObject("goodsList", goodsList);
		return modelAndView;
	}
	
	/**
	 * Mobile web - goodsDetail
	 */
	@RequestMapping(value="goods/goodsDetail")
	public ModelAndView goodsDetail(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("goods/goodsDetail");
		logger.info("* Welcome goodsDetail!!");
		
		if(authentication != null) {
			User user = (User)authentication.getPrincipal();
			logger.info("* GoodsController goodsDetail() user.getUsername(): " + user.getUsername());
		}
		return modelAndView;
	}
	
}
