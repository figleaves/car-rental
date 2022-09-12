package com.demo.carrental.config;

import com.demo.carrental.common.ErrorCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<RequestParameter> parameters = Arrays.asList(
                new RequestParameterBuilder()
                        .name("token")
                        .description("login api no need")
//                        .required(true)
                        .in(ParameterType.HEADER)
                        .build()
        );

        List<Response> responses = new ArrayList<>();
        for (ErrorCode errorCode : ErrorCode.values()){
            responses.add(new ResponseBuilder()
                            .code(String.valueOf(errorCode.getErrorCode()))
                            .description(errorCode.getErrorMsg())
                            .build());
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .globalRequestParameters(parameters)
                .globalResponses(HttpMethod.GET, responses)
                .globalResponses(HttpMethod.POST, responses)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.carrental"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Car Rental Project Backend System Api")
                .description("for simple demo project")
                .version("0.10")
                .build();
    }
}
