package com.cmi.mall.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cmi.mall.db.domain.LitemallUser;
import com.cmi.mall.db.service.LitemallUserService;
import com.cmi.mall.wx.dao.UserInfo;

@Service
public class UserInfoService {
    @Autowired
    private LitemallUserService userService;


    public UserInfo getInfo(Integer userId) {
        LitemallUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
        return userInfo;
    }
}
