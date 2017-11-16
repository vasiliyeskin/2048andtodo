package com.example.big_appls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class BigApplsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BigApplsApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BigApplsApplication.class, args);
	}

/*	public static void main(String[] args) {
		SpringApplication.run(BigApplsApplication.class, args);
	}*/
}
