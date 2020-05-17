package com.example.springbootmybatis.entity.UtilEntity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkSessionIdInterceptor()).addPathPatterns("/wx/**");
        registry.addInterceptor(checkTokenInterceptor()).addPathPatterns("/admin/**");
//        registry.addInterceptor(checkSessionIdInterceptor()).addPathPatterns("/user/*");
    }
    @Bean
    public CheckSessionIdInterceptor checkSessionIdInterceptor(){
        return new CheckSessionIdInterceptor();
    }
    @Bean
    public  CheckTokenInterceptor checkTokenInterceptor(){
        return new CheckTokenInterceptor();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")

                .allowedOrigins("*")
                .allowedHeaders("*")

                .allowCredentials(true)

                .allowedMethods("GET", "POST", "DELETE", "PUT")

                .maxAge(3600);

    }
}


