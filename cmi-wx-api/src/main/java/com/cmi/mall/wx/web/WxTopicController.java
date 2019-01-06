package com.cmi.mall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmi.mall.core.util.ResponseUtil;
import com.cmi.mall.core.validator.Order;
import com.cmi.mall.core.validator.Sort;
import com.cmi.mall.db.domain.LitemallGoods;
import com.cmi.mall.db.domain.LitemallTopic;
import com.cmi.mall.db.service.LitemallGoodsService;
import com.cmi.mall.db.service.LitemallTopicService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题服务
 */
@RestController
@RequestMapping("/wx/topic")
@Validated
public class WxTopicController {
    private final Log logger = LogFactory.getLog(WxTopicController.class);

    @Autowired
    private LitemallTopicService topicService;
    @Autowired
    private LitemallGoodsService goodsService;

    /**
     * 专题列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 专题列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallTopic> topicList = topicService.queryList(page, size, sort, order);
        int total = topicService.queryTotal();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", topicList);
        data.put("count", total);
        return ResponseUtil.ok(data);
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @GetMapping("detail")
    public Object detail(@NotNull Integer id) {
        Map<String, Object> data = new HashMap<>();
        LitemallTopic topic = topicService.findById(id);
        data.put("topic", topic);
        List<LitemallGoods> goods = new ArrayList<>();
        for (Integer i : topic.getGoods()) {
            LitemallGoods good = goodsService.findByIdVO(i);
            if (null != good)
                goods.add(good);
        }
        data.put("goods", goods);
        return ResponseUtil.ok(data);
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @GetMapping("related")
    public Object related(@NotNull Integer id) {
        List<LitemallTopic> topicRelatedList = topicService.queryRelatedList(id, 0, 4);
        return ResponseUtil.ok(topicRelatedList);
    }
}