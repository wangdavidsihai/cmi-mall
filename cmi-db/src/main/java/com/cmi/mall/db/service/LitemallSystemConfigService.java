package com.cmi.mall.db.service;

import org.springframework.stereotype.Service;

import com.cmi.mall.db.dao.LitemallSystemMapper;
import com.cmi.mall.db.domain.LitemallSystem;
import com.cmi.mall.db.domain.LitemallSystemExample;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LitemallSystemConfigService {
    @Resource
    private LitemallSystemMapper systemMapper;

    public List<LitemallSystem> queryAll() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or();
        return systemMapper.selectByExample(example);
    }
}
