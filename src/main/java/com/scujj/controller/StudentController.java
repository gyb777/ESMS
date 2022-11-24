package com.scujj.controller;

import com.scujj.entity.Result;

import java.util.HashMap;
import java.util.List;

public interface StudentController {
    Result insertStudentList(List<HashMap<String,Object>> studentList);
}
