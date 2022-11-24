package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.MajorEntity;
import com.scujj.mapper.MajorMapper;
import com.scujj.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, MajorEntity> implements MajorService {
    @Autowired
    MajorMapper majorMapper;

    @Override
    public boolean insertMajor(String name, Integer id) {
        return majorMapper.addOne(name, id) > 0;
    }

    @Override
    public Page<MajorEntity> pageWithCollege(Long page, Long limit, List<Integer> collegeIdList, String nameKey) {
        List<MajorEntity> majorEntityList = majorMapper.listWithCollege((page - 1) * limit, limit, collegeIdList, nameKey);
        Page<MajorEntity> page1 = new Page<>();
        Long total = Long.valueOf(majorMapper.selectCountWithCollege(collegeIdList, nameKey));
        Long pages = total / limit + 1;
        page1.setTotal(total);
        page1.setPages(pages);
        page1.setRecords(majorEntityList);
        return page1;
    }

    @Override
    public boolean deleteMajor(List<Integer> ids) {
        return majorMapper.deleteWithCollegeAndClassAndStudent(ids) > 0;
    }
}
