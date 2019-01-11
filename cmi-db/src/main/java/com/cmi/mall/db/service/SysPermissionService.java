package com.cmi.mall.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmi.mall.db.dao.SysPermissionMapper;
import com.cmi.mall.db.domain.SysPermission;
import com.cmi.mall.db.domain.SysPermissionExample;

@Service
public class SysPermissionService {
	@Resource
	private SysPermissionMapper permissionMapper;

	public SysPermission findById(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	public List<SysPermission> querySelective() {
		SysPermissionExample example = new SysPermissionExample();
		SysPermissionExample.Criteria criteria = example.createCriteria();

//		if (!StringUtils.isEmpty(username)) {
//			criteria.andUsernameLike("%" + username + "%");
//		}
//		if (!StringUtils.isEmpty(mobile)) {
//			criteria.andMobileEqualTo(mobile);
//		}
//		criteria.andDeletedEqualTo(false);

//		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//			example.setOrderByClause(sort + " " + order);
//		}

//		PageHelper.startPage(page, size);
		return permissionMapper.selectByExample(example);
	}

}
