package com.cmi.mall.api.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cmi.mall.api.annotation.support.LoginAdminHandlerMethodArgumentResolver;

@Configuration
public class AdminWebMvcConfigurer implements WebMvcConfigurer {
//    @Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new LoginAdminHandlerMethodArgumentResolver());
	}
}
