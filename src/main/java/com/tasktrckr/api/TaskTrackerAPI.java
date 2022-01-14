package com.tasktrckr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.tasktrckr.api.*")
@EntityScan("com.tasktrckr.api.jpa.entities")
@EnableJpaRepositories("com.tasktrckr.api.jpa.repositories")
public class TaskTrackerAPI {
	public static void main(String[] args) {

		SpringApplication.run(TaskTrackerAPI.class, args);
	}
}