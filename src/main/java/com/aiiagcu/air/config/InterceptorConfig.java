package com.aiiagcu.air.config;

import com.aiiagcu.air.util.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Register login interceptor
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/*.css", "/*.ico", "/error", "/logo.png", "/user/**", "/people/**", "/post/{id}", "/post/list/{pageNumber}",
                        "/calendar/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/**" ,"/actuator/**");
        
    }
}
