package com.cmi.mall.wx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cmi.mall.wx.annotation.support.LoginUserHandlerMethodArgumentResolver;

import java.util.List;

@Configuration
public class WxWebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }
}
