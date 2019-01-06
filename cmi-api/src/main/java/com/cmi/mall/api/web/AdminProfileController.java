package com.cmi.mall.api.web;

import static com.cmi.mall.api.util.AdminResponseCode.ADMIN_ALTER_NOT_ALLOWED;
import static com.cmi.mall.api.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.mall.api.annotation.LoginAdmin;
import com.cmi.mall.core.util.JacksonUtil;
import com.cmi.mall.core.util.ResponseUtil;
import com.cmi.mall.core.util.bcrypt.BCryptPasswordEncoder;
import com.cmi.mall.db.domain.LitemallAdmin;
import com.cmi.mall.db.service.LitemallAdminService;

@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {
    private final Log logger = LogFactory.getLog(AdminProfileController.class);

    @Autowired
    private LitemallAdminService adminService;

    @PostMapping("/password")
    public Object create(@LoginAdmin Integer adminId, @RequestBody String body) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        String oldPassword = JacksonUtil.parseString(body, "oldPassword");
        String newPassword = JacksonUtil.parseString(body, "newPassword");
        if (StringUtils.isEmpty(oldPassword)) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(newPassword)) {
            return ResponseUtil.badArgument();
        }

        LitemallAdmin admin = adminService.findAdmin(adminId);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(oldPassword, admin.getPassword())) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "账号密码不对");
        }

        String encodedNewPassword = encoder.encode(newPassword);
        admin.setPassword(encodedNewPassword);

        adminService.updateById(admin);
        return ResponseUtil.ok();
    }

}
