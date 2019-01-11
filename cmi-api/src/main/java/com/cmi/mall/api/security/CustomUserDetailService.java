package com.cmi.mall.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cmi.mall.db.domain.LitemallAdmin;
import com.cmi.mall.db.service.LitemallAdminService;
import com.cmi.mall.db.service.LitemallUserService;

/**
 * @author David Wang
 *
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private LitemallAdminService adminService;

	@Autowired
	private LitemallUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<LitemallAdmin> adminList = adminService.findAdmin(username);
		if (adminList == null) {
			throw new UsernameNotFoundException("no user");
		}
		SecurityUser userDetails = new SecurityUser(adminList.get(0).getId().toString(), username,
				adminList.get(0).getPassword(), !adminList.get(0).getDeleted(),

				true, true, true, grantedAuthorities(adminList.get(0).getId()), "", "");
		return userDetails;
	}

	protected Collection<GrantedAuthority> grantedAuthorities(Integer userId) {
//		List<Role> resources = roleRepository.getRoles(userId);
//		if (CollectionUtils.isEmpty(resources)) {
//			return new ArrayList<>();
//		}
		Collection<GrantedAuthority> authorities = new HashSet<>();
		// 忽略已经禁用的角色
//		resources.stream().filter(role -> !role.isDisabled()).forEach((resource -> {
//			authorities.add(new SimpleGrantedAuthority(resource.getName()));
//		}));
		return authorities;
	}

}
