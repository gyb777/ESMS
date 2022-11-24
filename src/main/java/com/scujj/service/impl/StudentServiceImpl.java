package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.StudentEntity;
import com.scujj.mapper.StudentMapper;
import com.scujj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    @Transactional
    public boolean addStudentList(List<HashMap<String, Object>> list) {
        String[] strings = {"name","class_id","student_number","phone_number","password"};
        for (HashMap<String, Object> map : list) {
            for (String s:strings) {
                if(map.get(s)==null){
                    return false;
                }
            }
            if (studentMapper.insertStudent((String) map.get("name"),
                    (Integer) map.get("class_id"),
                    (String) map.get("student_number"),
                    (String) map.get("phone_number"),
                    (String) map.get("password")) < 1) {
                return false;
            }
        }
        return true;
    }
}
