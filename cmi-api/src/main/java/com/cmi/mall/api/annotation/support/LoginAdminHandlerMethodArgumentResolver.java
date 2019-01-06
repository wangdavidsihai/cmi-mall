package com.cmi.mall.api.annotation.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cmi.mall.api.annotation.LoginAdmin;
import com.cmi.mall.api.service.AdminTokenManager;

public class LoginAdminHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	public static final String LOGIN_TOKEN_KEY = "X-Litemall-Admin-Token";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(Integer.class)
				&& parameter.hasParameterAnnotation(LoginAdmin.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request,
			WebDataBinderFactory factory) throws Exception {

		String token = request.getHeader(LOGIN_TOKEN_KEY);
		if (token == null || token.isEmpty()) {
			return null;
		}
		return AdminTokenManager.getUserId(token);
	}
}
