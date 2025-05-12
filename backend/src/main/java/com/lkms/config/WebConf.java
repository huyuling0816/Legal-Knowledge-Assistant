package com.lkms.config;

import com.lkms.auth.JwtInterceptor;
import com.lkms.utils.conventer.StringToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConf implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }

    @Resource
    JwtInterceptor jwtInterceptor;

    @Resource
    CorsConfig corsConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsConfig);
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**");
    }
}
