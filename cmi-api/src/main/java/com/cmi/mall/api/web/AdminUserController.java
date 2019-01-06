package com.cmi.mall.api.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cmi.mall.api.annotation.LoginAdmin;
import com.cmi.mall.core.util.RegexUtil;
import com.cmi.mall.core.util.ResponseUtil;
import com.cmi.mall.core.util.bcrypt.BCryptPasswordEncoder;
import com.cmi.mall.core.validator.Order;
import com.cmi.mall.core.validator.Sort;
import com.cmi.mall.db.domain.LitemallUser;
import com.cmi.mall.db.service.LitemallUserService;

import javax.validation.constraints.NotEmpty;

import static com.cmi.mall.api.util.AdminResponseCode.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    private final Log logger = LogFactory.getLog(AdminUserController.class);

    @Autowired
    private LitemallUserService userService;

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String username, String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        List<LitemallUser> userList = userService.querySelective(username, mobile, page, limit, sort, order);
        int total = userService.countSeletive(username, mobile, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", userList);

        return ResponseUtil.ok(data);
    }

    @GetMapping("/username")
    public Object username(@LoginAdmin Integer adminId, @NotEmpty String username) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        int total = userService.countSeletive(username, null, null, null, null, null);
        if (total == 0) {
            return ResponseUtil.ok("不存在");
        }
        return ResponseUtil.ok("已存在");
    }

    private Object validate(LitemallUser user) {
        String username = user.getUsername();
        if (StringUtils.isEmpty(user)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(username)) {
            return ResponseUtil.fail(USER_INVALID_NAME, "用户名不符合规定");
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            return ResponseUtil.fail(USER_INVALID_PASSWORD, "用户密码长度不能小于6");
        }
        String mobile = user.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileExact(mobile)) {
            return ResponseUtil.fail(USER_INVALID_MOBILE, "用户手机号码格式不正确");
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallUser user) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(user);
        if (error != null) {
            return error;
        }
        String username = user.getUsername();
        String mobile = user.getMobile();
        List<LitemallUser> userList = userService.queryByUsername(username);
        if (userList.size() > 0) {
            return ResponseUtil.fail(USER_NAME_EXIST, "用户名已注册");
        }
        userList = userService.queryByMobile(mobile);
        if (userList.size() > 0) {
            return ResponseUtil.fail(USER_MOBILE_EXIST, "手机号已注册");
        }
        if (!RegexUtil.isMobileExact(mobile)) {
            return ResponseUtil.fail(USER_INVALID_MOBILE, "手机号格式不正确");
        }

        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        userService.add(user);
        return ResponseUtil.ok(user);
    }

    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallUser user) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(user);
        if (error != null) {
            return error;
        }
        // 用户密码加密存储
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        if (userService.updateById(user) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(user);
    }
}
