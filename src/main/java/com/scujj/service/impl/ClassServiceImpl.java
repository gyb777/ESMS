package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.ClassEntity;
import com.scujj.mapper.ClassMapper;
import com.scujj.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, ClassEntity> implements ClassService {
    @Autowired
    ClassMapper classMapper;

    @Override
    public boolean insertClass(String name, Integer majorId) {
        return classMapper.addOne(name, majorId) > 0;
    }

    @Override
    public Page<ClassEntity> pageWithMajor(Page<ClassEntity> page, Integer collegeId, Integer majorId, String key) {
        long current = page.getCurrent();
        long size = page.getSize();
        page.setRecords(classMapper.listClassWithMajor((current - 1) * size, size, collegeId, majorId, key));
        long total = classMapper.countClassWithMajor((current - 1) * size, size, collegeId, majorId, key);
        page.setTotal(total);
        page.setPages(total / size + 1);
        return page;
    }
}
