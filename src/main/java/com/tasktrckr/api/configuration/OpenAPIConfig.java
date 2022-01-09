package com.tasktrckr.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenAPIConfig {
	public @Bean OpenAPI taskTrackerAPIDoc() {
		return new OpenAPI().info(new Info().title("Task Tracker API")
				.description("A CRUD API for the task tracker system").version("v1")
				.license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
	}
}
