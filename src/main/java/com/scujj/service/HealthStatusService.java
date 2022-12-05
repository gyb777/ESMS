package com.scujj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.HealthStatusEntity;
import com.scujj.entity.LeaveEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface HealthStatusService extends IService<HealthStatusEntity> {
    HashMap<String, Object> getHealthList(Integer studentId, Page<HealthStatusEntity> myPage, Date startTime, Date endTime);

    HashMap<String, Object> getHealthList(Page<LeaveEntity> myPage,
                                          Date time,
                                          List<Integer> collegeIdList,
                                          List<Integer> majorIdList,
                                          List<Integer> classIdList,
                                          Boolean isClock,
                                          Boolean isException);

    HashMap<String, Object> getHasRiskArea(Page<LeaveEntity> myPage,
                                           Date startTime,
                                           Date endTime,
                                           List<Integer> collegeIdList,
                                           List<Integer> majorIdList,
                                           List<Integer> classIdList,
                                           Integer range);
}
