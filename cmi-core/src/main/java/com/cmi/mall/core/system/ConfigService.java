package com.cmi.mall.core.system;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmi.mall.db.domain.LitemallSystem;
import com.cmi.mall.db.service.LitemallSystemConfigService;

/**
 * 该类用于自动初始化数据库配置到BaseConfig中，以便BaseConfig的子类调用
 */
@Component
class ConfigService {
	private static ConfigService systemConfigService;
	@Autowired
	private LitemallSystemConfigService litemallSystemConfigService;

	// 不允许实例化
	private ConfigService() {

	}

	static ConfigService getSystemConfigService() {
		return systemConfigService;
	}

	@PostConstruct
	public void inist() {
		systemConfigService = this;
		systemConfigService.inistConfigs();
	}

	/**
	 * 根据 prefix 重置该 prefix 下所有设置
	 *
	 * @param prefix
	 */
	public void reloadConfig(String prefix) {
		List<LitemallSystem> list = litemallSystemConfigService.queryAll();
		for (LitemallSystem item : list) {
			// 符合条件，添加
			if (item.getKeyName().startsWith(prefix))
				BaseConfig.addConfig(item.getKeyName(), item.getKeyValue());
		}
	}

	/**
	 * 读取全部配置
	 */
	private void inistConfigs() {
		List<LitemallSystem> list = litemallSystemConfigService.queryAll();
		for (LitemallSystem item : list) {
			BaseConfig.addConfig(item.getKeyName(), item.getKeyValue());
		}
	}
}