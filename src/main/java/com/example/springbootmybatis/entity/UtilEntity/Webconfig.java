package com.example.springbootmybatis.entity.UtilEntity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkSessionIdInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(checkSessionIdInterceptor()).addPathPatterns("/user/*");
    }
    @Bean
    public CheckSessionIdInterceptor checkSessionIdInterceptor(){
        return new CheckSessionIdInterceptor();
    }
}
