package com.scujj.controller;

import com.scujj.entity.Result;

import java.util.HashMap;
import java.util.List;

public interface HealthStatusController {
    Result addOrUpdateHealthStatus(HashMap<String, Object> map);

    Result selectByStudentId(Integer studentId, Long page, Long limit, String startTime, String endTime);

    Result selectByAdmin(Long page, Long limit, String time, List<Integer> collegeIdList, List<Integer> majorIdList, List<Integer> classIdList, Boolean isClock, Boolean isException);

    Result selectHasRiskArea(Long page,
                             Long limit,
                             String startTime,
                             String endTime,
                             List<Integer> collegeIdList,
                             List<Integer> majorIdList,
                             List<Integer> classIdList,
                             Integer range);
}
