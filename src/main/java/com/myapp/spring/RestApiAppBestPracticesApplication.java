package com.myapp.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
public class RestApiAppBestPracticesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiAppBestPracticesApplication.class, args);
	}
	
	@Bean
	ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

}
