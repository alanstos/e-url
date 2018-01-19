package org.hire.me.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.hire.me"))
                .paths(regex("/encurtar.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Hire.me API REST",
                "API REST de Hire.me",
                "1.0",
                "Terms of Service",
                new Contact("Alan Santos", "https://github.com/alanstos",
                        "alanstos@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", 
                new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
    
}
