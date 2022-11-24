package com.scujj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.StudentEntity;

import java.util.HashMap;
import java.util.List;

public interface StudentService extends IService<StudentEntity> {
    boolean addStudentList(List<HashMap<String, Object>> list);
}
