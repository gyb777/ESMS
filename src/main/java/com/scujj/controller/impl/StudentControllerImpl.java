package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scujj.controller.StudentController;
import com.scujj.entity.Result;
import com.scujj.entity.StudentEntity;
import com.scujj.service.ClassService;
import com.scujj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class StudentControllerImpl implements StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClassService classService;

    @Override
    @PostMapping("root/studentInfo")
    public Result insertStudentList(@RequestBody List<HashMap<String, Object>> studentList) {
        for (HashMap<String, Object> map : studentList) {
            QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("student_number", map.get("student_number"));
            if (studentService.getOne(wrapper) != null) {
                return new Result(2, "学号为" + map.get("student_number") + "的学生已存在", null);
            }
            if (classService.getById((Integer) map.get("class_id")) == null) {
                return new Result(2, "id=" + map.get("class_id") + "的班级不存在", null);
            }
        }
        if (studentService.addStudentList(studentList)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "数据不完整", null);
        }
    }
}
