package com.imranmadbar;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	  private final Logger logger = LoggerFactory.getLogger(this.getClass());
	  
	@GetMapping("/")
	public String index() {
		
	    logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
		
		return "Welcome to Logback (Default) application";
	}

}
