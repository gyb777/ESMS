package com.scujj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.MajorEntity;

import java.util.List;

public interface MajorService extends IService<MajorEntity> {
    boolean insertMajor(String name, Integer id);

    Page<MajorEntity> pageWithCollege(Long page,
                                      Long limit,
                                      List<Integer> collegeIdList,
                                      String nameKey);

    boolean deleteMajor(List<Integer> ids);
}
