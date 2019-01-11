package com.cmi.mall.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author David Wang
 */
public class SecurityUser extends User {
	private String uid;
	private String salt;
	private String email;

	public SecurityUser(String uid, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String salt, String email) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.salt = salt;
		this.uid = uid;
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public String getUid() {
		return uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
