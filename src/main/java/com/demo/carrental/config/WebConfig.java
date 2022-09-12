package com.demo.carrental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private TokenFilter tokenFilter;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenFilter)
                .addPathPatterns("/carRental/**")
                .excludePathPatterns(
                        "/carRental/customer/login");
//                        "/swagger-ui");
//                        "/v2/**",
//                        "/swagger-resources/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*");//"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
    }
}
