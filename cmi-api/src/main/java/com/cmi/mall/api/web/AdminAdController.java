package com.cmi.mall.api.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.mall.api.annotation.LoginAdmin;
import com.cmi.mall.core.util.ResponseUtil;
import com.cmi.mall.core.validator.Order;
import com.cmi.mall.core.validator.Sort;
import com.cmi.mall.db.domain.LitemallAd;
import com.cmi.mall.db.service.LitemallAdService;

@RestController
@RequestMapping("/admin/ad")
@Validated
public class AdminAdController {
	private final Log logger = LogFactory.getLog(AdminAdController.class);

	@Autowired
	private LitemallAdService adService;

	@GetMapping("/list")
	public Object list(@LoginAdmin Integer adminId, String name, String content,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		if (adminId == null) {
			return ResponseUtil.unlogin();
		}

		List<LitemallAd> adList = adService.querySelective(name, content, page, limit, sort, order);
		int total = adService.countSelective(name, content, page, limit, sort, order);
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", adList);

		return ResponseUtil.ok(data);
	}

	private Object validate(LitemallAd ad) {
		String name = ad.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}
		String content = ad.getContent();
		if (StringUtils.isEmpty(content)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@PostMapping("/create")
	public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallAd ad) {
		if (adminId == null) {
			return ResponseUtil.unlogin();
		}
		Object error = validate(ad);
		if (error != null) {
			return error;
		}
		adService.add(ad);
		return ResponseUtil.ok(ad);
	}

	@GetMapping("/read")
	public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
		if (adminId == null) {
			return ResponseUtil.unlogin();
		}

		LitemallAd brand = adService.findById(id);
		return ResponseUtil.ok(brand);
	}

	@PostMapping("/update")
	public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallAd ad) {
		if (adminId == null) {
			return ResponseUtil.unlogin();
		}
		Object error = validate(ad);
		if (error != null) {
			return error;
		}
		if (adService.updateById(ad) == 0) {
			return ResponseUtil.updatedDataFailed();
		}

		return ResponseUtil.ok(ad);
	}

	@PostMapping("/delete")
	public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallAd ad) {
		if (adminId == null) {
			return ResponseUtil.unlogin();
		}
		Integer id = ad.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		adService.deleteById(id);
		return ResponseUtil.ok();
	}

}
