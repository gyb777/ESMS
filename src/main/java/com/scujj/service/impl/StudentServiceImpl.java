package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.StudentEntity;
import com.scujj.mapper.StudentMapper;
import com.scujj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    @Transactional
    public boolean addStudentList(List<HashMap<String, Object>> list) {
        String[] strings = {"name", "class_id", "student_number", "password"};
        for (HashMap<String, Object> map : list) {
            for (String s : strings) {
                if (map.get(s) == null) {
                    return false;
                }
            }
            if (studentMapper.insertStudent((String) map.get("name"),
                    (Integer) map.get("class_id"),
                    (String) map.get("student_number"),
                    (String) map.get("password")) < 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public HashMap<String, Object> getStudentList(Page<StudentEntity> page,
                                                  Integer collegeId,
                                                  Integer majorId,
                                                  Integer classId,
                                                  String key) {
        long current = page.getCurrent();
        long size = page.getSize();
        List<StudentEntity> studentEntityList = studentMapper.listStudentWithClass((current - 1) * size,
                size, collegeId, majorId, classId, key);
        long total = studentMapper.countStudentWithClass((current - 1) * size,
                size, collegeId, majorId, classId, key);
        long pages = total / size + 1;
        long inSchool = studentMapper.countInSchool((current - 1) * size,
                size, collegeId, majorId, classId, key);
        long outSchool = total - inSchool;
        long health = studentMapper.countHealth((current - 1) * size,
                size, collegeId, majorId, classId, key);
        long diagnosis = studentMapper.countDiagnosis((current - 1) * size,
                size, collegeId, majorId, classId, key);
        long suspected = total - health - diagnosis;
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("pages", pages);
        data.put("inSchool", inSchool);
        data.put("outSchool", outSchool);
        data.put("health", health);
        data.put("suspected", suspected);
        data.put("diagnosis",diagnosis);
        List<HashMap<String, Object>> studentList = new ArrayList<>();
        for (StudentEntity student : studentEntityList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", student.getId());
            map.put("name", student.getName());
            map.put("studentNumber", student.getStudentNumber());
            map.put("classId", student.getClassEntity().getId());
            map.put("className", student.getClassEntity().getName());
            map.put("phoneNumber", student.getPhoneNumber());
            map.put("password", student.getPassword());
            map.put("inSchool", student.getInSchool());
            map.put("healthStatus", student.getHealthStatus());
            studentList.add(map);
        }
        data.put("studentList", studentList);
        return data;
    }
}
