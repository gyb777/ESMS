package com.scujj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.LeaveEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface LeaveService extends IService<LeaveEntity> {
    boolean addOne(Integer studentId,
                   Date startTime,
                   Date endTime,
                   String reason,
                   String imgSrc,
                   String province,
                   String city,
                   String county,
                   String position);

    HashMap<String, Object> getLeaveList(Page<LeaveEntity> myPage,
                                         Date startTime,
                                         Date endTime,
                                         List<Integer> collegeIdList,
                                         List<Integer> majorIdList,
                                         List<Integer> classIdList,
                                         Integer status);
}
