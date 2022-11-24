package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.StudentController;
import com.scujj.entity.Result;
import com.scujj.entity.StudentEntity;
import com.scujj.service.ClassService;
import com.scujj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @DeleteMapping("root/studentInfo")
    @Transactional
    public Result removeStudentList(@RequestBody List<Integer> studentIdList) {
        for (Integer id : studentIdList) {
            if (studentService.getById(id) == null) {
                return new Result(2, "id=" + id + "的学生不存在", null);
            }
        }
        if (studentService.removeByIds(studentIdList)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }

    @Override
    @PutMapping("root/studentInfo")
    public Result updateStudent(@RequestBody HashMap<String, Object> map) {
        UpdateWrapper<StudentEntity> wrapper = new UpdateWrapper<>();
        Integer id = (Integer) map.get("id");
        if (studentService.getById(id) == null) {
            return new Result(2, "id=" + id + "的学生不存在", null);
        }
        wrapper.eq("id", id);
        for (String key : map.keySet()) {
            if (key.equals("id")) {
                continue;
            }
            if (map.get(key) != null) {
                wrapper.set(key, map.get(key));
            }
        }
        if (studentService.update(wrapper)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }

    @Override
    @GetMapping("root/studentInfo")
    public Result getStudentList(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                 @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                                 @RequestParam(value = "id", required = false) Integer collegeId,
                                 @RequestParam(value = "major_id", required = false) Integer majorId,
                                 @RequestParam(value = "class_id", required = false) Integer classId,
                                 @RequestParam(value = "key", required = false) String key) {
        Page<StudentEntity> page1 = new Page<>(page, limit);
        HashMap<String, Object> data = studentService.getStudentList(page1, collegeId, majorId, classId, key);
        return new Result(1, "成功", data);
    }
}
