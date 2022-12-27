package com.chan.springsecurity.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
       registry.viewResolver(mustacheViewResolver());
    }

    private MustacheViewResolver mustacheViewResolver() {
        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setCharset("UTF-8");
        mustacheViewResolver.setContentType("text/html; charset=UTF-8");
        mustacheViewResolver.setPrefix("classpath:/templates/");
        mustacheViewResolver.setSuffix(".html");

        return mustacheViewResolver;
    }
}
