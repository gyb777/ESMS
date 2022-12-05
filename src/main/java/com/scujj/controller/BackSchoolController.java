package com.scujj.controller;

import com.scujj.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface BackSchoolController {
    Result addOrUpdateBackSchool(Integer id, Integer studentId, String end, String reason, MultipartFile img, Integer memberNumber, String member);

    Result removeBackSchool(HashMap<String, Integer> map);

    Result getBackSchoolList(Integer id, Long page, Long limit, Integer status);

    Result updateBackSchool(HashMap<String, Object> map);

    Result getBackSchoolList(Long page,
                             Long limit,
                             String startTime,
                             String endTime,
                             List<Integer> collegeIdList,
                             List<Integer> majorIdList,
                             List<Integer> classIdList,
                             Integer status);
}
