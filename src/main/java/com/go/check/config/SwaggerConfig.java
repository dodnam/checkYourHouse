package com.go.check.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(swaggerInfo())
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.go.check.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false); 
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
        		.title("ViSiCa API Documentation")
                .description("Doc for visica_front")
                .version("1").build();
    }
}