package com.ayanokoujifl.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ayanokoujifl.helpdesk.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	@Bean
	public boolean instatianteDatabase() {
		if (value.equals("create")) {
			dbService.devInstantiate();
			return true;
		}else {
			return false;
		}
	}
}
