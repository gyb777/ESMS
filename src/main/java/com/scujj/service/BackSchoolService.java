package com.scujj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.BackSchoolEntity;
import com.scujj.entity.LeaveEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BackSchoolService extends IService<BackSchoolEntity> {
    boolean addOne(Integer studentId, Date endTime, String reason, String img, Integer memberNumber, String member);

    HashMap<String, Object> getBackSchoolList(Page<LeaveEntity> myPage,
                                         Date startTime,
                                         Date endTime,
                                         List<Integer> collegeIdList,
                                         List<Integer> majorIdList,
                                         List<Integer> classIdList,
                                         Integer status);
}
