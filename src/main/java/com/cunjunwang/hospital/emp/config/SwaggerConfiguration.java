package com.cunjunwang.hospital.emp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Generate API Document on Swagger2
 * Created by CunjunWang on 2018/11/25.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    // @Value("swagger.host")
    private String swaggerHost = "localhost:8080";

    @Bean
    public Docket createSwaggerAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cunjunwang.hospital.emp.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("[hospital-service]")
                .description("医院后台管理系统后端服务")
                .version("0.0.1")
                .build();
    }

}
