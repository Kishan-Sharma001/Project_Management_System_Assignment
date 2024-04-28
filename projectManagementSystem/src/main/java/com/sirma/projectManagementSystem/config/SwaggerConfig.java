package com.sirma.projectManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@Configuration
public class SwaggerConfig {
	 
	@Bean
	public OpenAPI openAPI() {
		
		return new OpenAPI()
				.info(new Info().title("Project Management API")
								.description("this is Project Management project Api ")
								.version("1.0")
								.contact(new Contact().name("Kishan Sharma").email("krish.sharma.351104@gmail.com")
										.url("linkedin.com/in/kishan-sharma-03b8baa6"))
								.license(new License().name("apache"))
								)
				.externalDocs(new ExternalDocumentation().url("github.com/Kishan-Sharma001").description("this is github profile of kishan sharma"));
	}


}
