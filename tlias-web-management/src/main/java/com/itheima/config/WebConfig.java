package com.itheima.config;

import com.itheima.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration

public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginCheckInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/user/users/login")
                .excludePathPatterns("/admins/login").excludePathPatterns("/user/users").excludePathPatterns("/admins").excludePathPatterns("/fileUpload");

    }
}

