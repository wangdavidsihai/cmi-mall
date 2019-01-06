package com.cmi.mall.api.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cmi.mall.api.annotation.LoginAdmin;
import com.cmi.mall.core.util.ResponseUtil;
import com.cmi.mall.core.validator.Order;
import com.cmi.mall.core.validator.Sort;
import com.cmi.mall.db.domain.LitemallCoupon;
import com.cmi.mall.db.domain.LitemallCouponUser;
import com.cmi.mall.db.domain.LitemallTopic;
import com.cmi.mall.db.service.LitemallCouponService;
import com.cmi.mall.db.service.LitemallCouponUserService;
import com.cmi.mall.db.service.LitemallTopicService;
import com.cmi.mall.db.util.CouponConstant;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/coupon")
@Validated
public class AdminCouponController {
    private final Log logger = LogFactory.getLog(AdminCouponController.class);

    @Autowired
    private LitemallCouponService couponService;
    @Autowired
    private LitemallCouponUserService couponUserService;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String name, Short type, Short status,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCoupon> couponList = couponService.querySelective(name, type, status, page, limit, sort, order);
        int total = couponService.countSelective(name, type, status, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", couponList);

        return ResponseUtil.ok(data);
    }

    @GetMapping("/listuser")
    public Object listuser(@LoginAdmin Integer adminId,
                       Integer userId, Integer couponId, Short status,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallCouponUser> couponList = couponUserService.queryList(userId, couponId, status, page, limit, sort, order);
        int total = couponUserService.countList(userId, couponId, status, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", couponList);

        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallCoupon coupon) {
        String name = coupon.getName();
        if(StringUtils.isEmpty(name)){
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallCoupon coupon) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(coupon);
        if (error != null) {
            return error;
        }

        // 如果是兑换码类型，则这里需要生存一个兑换码
        if (coupon.getType().equals(CouponConstant.TYPE_CODE)){
            String code = couponService.generateCode();
            coupon.setCode(code);
        }

        couponService.add(coupon);
        return ResponseUtil.ok(coupon);
    }

    @GetMapping("/read")
    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        LitemallCoupon coupon = couponService.findById(id);
        return ResponseUtil.ok(coupon);
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallCoupon coupon) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(coupon);
        if (error != null) {
            return error;
        }
        if (couponService.updateById(coupon) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(coupon);
    }

    @PostMapping("/delete")
    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallCoupon coupon) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        couponService.deleteById(coupon.getId());
        return ResponseUtil.ok();
    }

}
