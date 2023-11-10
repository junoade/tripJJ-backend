package com.trip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		log.debug("swagger UI");
		final ApiInfo apiInfo = new ApiInfoBuilder()
				.title("ENJOY TRIP API")
				.description("<h3>워크샵에서 사용되는 REST API에 대한 문서를 제공합니다.</h3>")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@email.com"))
				.license("SSAFY License")
				.version("1.0")
				.build();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.trip.controller"))
				.paths(PathSelectors.ant("/*/board/**"))
				.build();
	}
}
