package com.scujj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.StudentEntity;

import java.util.HashMap;
import java.util.List;

public interface StudentService extends IService<StudentEntity> {
    boolean addStudentList(List<HashMap<String, Object>> list);

    HashMap<String,Object> getStudentList(Page<StudentEntity> page,
                                       Integer collegeId,
                                       Integer majorId,
                                       Integer classId,
                                       String key);

    StudentEntity getStudentByPhoneNumber(String phoneNumber);
    StudentEntity getStudentById(Integer id);
    StudentEntity getStudentByNumber(String studentNumber);
}
