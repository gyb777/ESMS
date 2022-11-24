package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.RootEntity;
import com.scujj.mapper.RootMapper;
import com.scujj.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootServiceImpl extends ServiceImpl<RootMapper, RootEntity> implements RootService {
    @Autowired
    RootMapper rootMapper;

    @Override
    public int insert(RootEntity rootEntity) {
        return rootMapper.insert(rootEntity);
    }
}
