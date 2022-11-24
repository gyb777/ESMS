package com.scujj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.RootEntity;

public interface RootService extends IService<RootEntity> {
    int insert(RootEntity rootEntity);
}
