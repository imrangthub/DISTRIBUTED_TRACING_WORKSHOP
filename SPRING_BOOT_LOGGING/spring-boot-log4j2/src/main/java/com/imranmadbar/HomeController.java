package com.imranmadbar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {
		
		final Logger logger = LogManager.getLogger(HomeController.class);
		
//		logger.debug("Hello from Log4j 2 - num : {}", () -> "This is a simple log Message");
		logger.info("Hello from Log4j 2 - num : {}", () -> "This is a simple log Message");
		
		return "Welcome to Log4j2 application";
	}

}
