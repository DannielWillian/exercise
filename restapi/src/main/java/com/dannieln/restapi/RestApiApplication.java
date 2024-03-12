package com.dannieln.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the entrypoint of the REST API service. It loads the Spring Context and boots
 * up the web server.
 */
@SpringBootApplication
public class RestApiApplication {

    /**
     * Loads the Spring Context and boots up the web server
     * 
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
