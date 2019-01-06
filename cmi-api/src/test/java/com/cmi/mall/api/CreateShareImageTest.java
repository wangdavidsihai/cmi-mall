package com.cmi.mall.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cmi.mall.core.qcode.QCodeService;
import com.cmi.mall.db.domain.LitemallGoods;
import com.cmi.mall.db.service.LitemallGoodsService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CreateShareImageTest {
    @Autowired
    QCodeService qCodeService;
    @Autowired
    LitemallGoodsService litemallGoodsService;

    @Test
    public void test() {
        LitemallGoods good = litemallGoodsService.findById(1181010);
        qCodeService.createGoodShareImage(good.getId().toString(), good.getPicUrl(), good.getName());
    }
}
