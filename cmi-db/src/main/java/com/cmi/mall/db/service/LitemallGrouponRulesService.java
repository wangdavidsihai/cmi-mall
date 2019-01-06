package com.cmi.mall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.cmi.mall.db.dao.LitemallGoodsMapper;
import com.cmi.mall.db.dao.LitemallGrouponRulesMapper;
import com.cmi.mall.db.domain.LitemallGoods;
import com.cmi.mall.db.domain.LitemallGrouponRules;
import com.cmi.mall.db.domain.LitemallGrouponRulesExample;
import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LitemallGrouponRulesService {
    @Resource
    private LitemallGrouponRulesMapper mapper;
    @Resource
    private LitemallGoodsMapper goodsMapper;
    private LitemallGoods.Column[] goodsColumns = new LitemallGoods.Column[]{LitemallGoods.Column.id, LitemallGoods.Column.name, LitemallGoods.Column.brief, LitemallGoods.Column.picUrl, LitemallGoods.Column.counterPrice, LitemallGoods.Column.retailPrice};

    public int createRules(LitemallGrouponRules rules) {
        rules.setAddTime(LocalDateTime.now());
        rules.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(rules);
    }

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    public LitemallGrouponRules queryById(Integer id) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<LitemallGrouponRules> queryByGoodsId(Integer goodsId) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }

    /**
     * 获取首页团购活动列表
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<Map<String, Object>> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<Map<String, Object>> queryList(int offset, int limit, String sort, String order) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        List<LitemallGrouponRules> grouponRules = mapper.selectByExample(example);

        List<Map<String, Object>> grouponList = new ArrayList<>(grouponRules.size());
        for (LitemallGrouponRules rule : grouponRules) {
            Integer goodsId = rule.getGoodsId();
            LitemallGoods goods = goodsMapper.selectByPrimaryKeySelective(goodsId, goodsColumns);
            if (goods == null)
                continue;

            Map<String, Object> item = new HashMap<>();
            item.put("goods", goods);
            item.put("groupon_price", goods.getRetailPrice().subtract(rule.getDiscount()));
            item.put("groupon_member", rule.getDiscountMember());
            grouponList.add(item);
        }

        return grouponList;
    }

    public int countList(int offset, int limit, String sort, String order) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.or().andDeletedEqualTo(false);
        return (int) mapper.countByExample(example);
    }

    /**
     * 判断某个团购活动是否已经过期
     *
     * @return
     */
    public boolean isExpired(LitemallGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购活动列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        LitemallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }

    public int countSelective(String goodsId, Integer page, Integer limit, String sort, String order) {
        LitemallGrouponRulesExample example = new LitemallGrouponRulesExample();
        LitemallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        return (int) mapper.countByExample(example);
    }

    public void delete(Integer id) {
        mapper.logicalDeleteByPrimaryKey(id);
    }

    public int updateById(LitemallGrouponRules grouponRules) {
        grouponRules.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(grouponRules);
    }
}