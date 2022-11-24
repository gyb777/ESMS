package com.scujj.controller;

import com.scujj.entity.Result;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MajorController {
    Result addMajor(HashMap<String, Object> map);

    Result getMajorList(Long limit, Long page, @Param("collegeId") List<Integer> idList, String key);

    Result deleteMajor(List<Integer> ids);

    Result updataMajor(HashMap<String, Object> map);
}
