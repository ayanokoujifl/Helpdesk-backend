package com.ayanokoujifl.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ayanokoujifl.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	DBService dbService;
	
	@Bean
	public void instatianteDatabase() {
		dbService.instantiateDatabase();
	}
}
