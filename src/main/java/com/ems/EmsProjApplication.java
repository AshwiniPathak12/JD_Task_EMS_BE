package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableSwagger2
public class EmsProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsProjApplication.class, args);
	}

	/*
	 * @Bean public Docket Api() { return new
	 * Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.
	 * basePackage("com.ems")) .build(); }
	 */
}
