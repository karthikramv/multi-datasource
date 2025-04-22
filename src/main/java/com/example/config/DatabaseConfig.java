package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.repository.StudentRepo;

@Configuration
public class DatabaseConfig {

	
	  @Bean public CommandLineRunner testDatabaseConnection(StudentRepo repo) {
	  return args -> { System.out.println("Database connection successful"); 
	  }; 
	  }
	 	
}
