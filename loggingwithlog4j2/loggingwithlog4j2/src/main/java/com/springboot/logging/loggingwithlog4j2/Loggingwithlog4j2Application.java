package com.springboot.logging.loggingwithlog4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Loggingwithlog4j2Application {
	private static final Logger logger = LoggerFactory.getLogger(Loggingwithlog4j2Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Loggingwithlog4j2Application.class, args);
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");

	}
}