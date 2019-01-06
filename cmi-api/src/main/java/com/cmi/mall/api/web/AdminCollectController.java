package com.cmi.mall.api.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.mall.api.annotation.LoginAdmin;
import com.cmi.mall.core.util.ResponseUtil;
import com.cmi.mall.core.validator.Order;
import com.cmi.mall.core.validator.Sort;
import com.cmi.mall.db.domain.LitemallCollect;
import com.cmi.mall.db.service.LitemallCollectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/collect")
@Validated
public class AdminCollectController {
    private final Log logger = LogFactory.getLog(AdminCollectController.class);

    @Autowired
    private LitemallCollectService collectService;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String userId, String valueId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCollect> collectList = collectService.querySelective(userId, valueId, page, limit, sort, order);
        int total = collectService.countSelective(userId, valueId, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", collectList);

        return ResponseUtil.ok(data);
    }
}
