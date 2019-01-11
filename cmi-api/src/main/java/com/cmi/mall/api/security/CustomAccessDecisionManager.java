package com.cmi.mall.api.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes.size() <= 0 || authentication == null) {
			return;
		}

		ConfigAttribute configAttr = null;
		// role from request
		String requestRole = "";
		for (Iterator<ConfigAttribute> it = configAttributes.iterator(); it.hasNext();) {
			configAttr = it.next();
			requestRole = configAttr.getAttribute();
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				if (requestRole.trim().equals(authority)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("no access!");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
