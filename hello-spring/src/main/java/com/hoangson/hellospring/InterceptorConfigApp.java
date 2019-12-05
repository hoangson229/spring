package com.hoangson.hellospring;

import com.hoangson.hellospring.interceptor.CheckSecretKeyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfigApp implements WebMvcConfigurer {
    @Autowired
    CheckSecretKeyInterceptor checkSecretKeyInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkSecretKeyInterceptor).addPathPatterns("/son/editname");
    }
}
