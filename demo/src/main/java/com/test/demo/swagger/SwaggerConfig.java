package com.test.demo.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("demo-api").packagesToScan("com.test.demo.controller").build();
	}

	@Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo API")
                        .description("API documentation for the Demo application")
                        .version("v1.0"));
	}
}
