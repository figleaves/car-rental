package com.demo.carrental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private TokenFilter tokenFilter;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/customer/login");
    }
}
