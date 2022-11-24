package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.CollegeEntity;
import com.scujj.mapper.CollegeMapper;
import com.scujj.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, CollegeEntity> implements CollegeService {
    @Autowired
    CollegeMapper collegeMapper;

    public boolean removeCollegeByIds(List<Integer> list) {
        return collegeMapper.removeCollege(list) > 0;
    }

    @Override
    public boolean insertCollege(String name) {
        return collegeMapper.addOne(name) > 0;
    }
}
