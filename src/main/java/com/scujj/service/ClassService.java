package com.scujj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.ClassEntity;

public interface ClassService extends IService<ClassEntity> {
    boolean insertClass(String name, Integer majorId);

    Page<ClassEntity> pageWithMajor(Page<ClassEntity> page, Integer collegeId, Integer majorId, String key);
}
