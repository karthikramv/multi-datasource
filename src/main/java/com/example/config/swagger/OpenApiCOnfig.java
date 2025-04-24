package com.example.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						name = "krmv",
						email = "krmv@gmail.com",
						url = "http://localhost:8081"
						),
				description = "OpenApi Documention for Multi_Datasource",
				title = "OpenAPi Specification - krmv",
				version = "1.0.0",
				license = @License(
						name = "License name",
						url = "http://some-url.com"
						),
				termsOfService = "Terms of service"
				),
		servers = {
				@Server(
						description = "LOCAL ENV",
						url = "http://localhost:8081/krmv"
						),
				@Server(
						description = "PROD ENV",
						url = "http://localhost:8081/krmv"
						)
		}
		)
public class OpenApiCOnfig {   
	
   
}