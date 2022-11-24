package com.scujj.controller;

import com.scujj.entity.CollegeEntity;
import com.scujj.entity.Result;

import java.util.HashMap;
import java.util.List;

public interface CollegeController {
    Result addCollege(HashMap<String, String> map);

    Result getCollegeList(Long page, Long limit, String key);

    Result updateCollege(CollegeEntity college);

    Result deleteCollegeList(List<Integer> list);
}
