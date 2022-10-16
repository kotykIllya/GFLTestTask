package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/searchPageExactResult").setViewName("searchPageExactResult");
        registry.addViewController("/searchPageBiggerThan").setViewName("searchPageBiggerThan");
        registry.addViewController("/searchPageLessThan").setViewName("searchPageLessThan");
    }

}