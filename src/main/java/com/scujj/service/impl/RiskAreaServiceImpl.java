package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.RiskAreaEntity;
import com.scujj.mapper.RiskAreaMapper;
import com.scujj.service.RiskAreaService;
import org.springframework.stereotype.Service;

@Service
public class RiskAreaServiceImpl extends ServiceImpl<RiskAreaMapper, RiskAreaEntity> implements RiskAreaService {
}
