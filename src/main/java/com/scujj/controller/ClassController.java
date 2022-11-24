package com.scujj.controller;

import com.scujj.entity.Result;

import java.util.HashMap;
import java.util.List;

public interface ClassController {
    Result addClass(HashMap<String, Object> map);

    Result getClassList(Long page, Long limit, Integer collegeId, Integer majorId, String key);

    Result updateClass(HashMap<String, Object> map);

    Result deleteClassList(List<Integer> list);
}
