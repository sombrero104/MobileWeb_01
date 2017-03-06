package sample.controller;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	private static final Logger logger = Logger.getLogger(TestController.class); 

	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value="test", method=RequestMethod.GET)
	public void test() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select id, pw, enabled from user";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
		for(Map<String,Object> result : resultList) {
			logger.info("* TestController test(): " + result.get("id") + " / " + result.get("pw") + " / " + result.get("enabled"));
		}
	}
	
}
