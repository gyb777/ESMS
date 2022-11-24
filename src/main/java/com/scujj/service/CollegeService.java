package com.scujj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.CollegeEntity;

import java.util.List;

public interface CollegeService extends IService<CollegeEntity> {
    boolean insertCollege(String name);

    boolean removeCollegeByIds(List<Integer> list);
}
