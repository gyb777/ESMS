package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.ClassController;
import com.scujj.entity.ClassEntity;
import com.scujj.entity.Result;
import com.scujj.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/root/class")
public class ClassControllerImpl implements ClassController {
    @Autowired
    ClassService classService;

    @Override
    @PostMapping
    public Result addClass(@RequestBody HashMap<String, Object> map) {
        String name = (String) map.get("class_name");
        Integer majorId = (Integer) map.get("major_id");
        QueryWrapper<ClassEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (classService.getOne(wrapper) != null) {
            return new Result(2, "班级已存在", null);
        }
        if (classService.insertClass(name, majorId)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }

    @Override
    @GetMapping
    public Result getClassList(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                               @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                               @RequestParam(value = "college_id", required = false) Integer collegeId,
                               @RequestParam(value = "major_id", required = false) Integer majorId,
                               @RequestParam(value = "key", required = false) String key) {
        Page<ClassEntity> ipage = new Page<>(page, limit);
        Page<ClassEntity> page2 = classService.pageWithMajor(ipage, collegeId, majorId, key);
        List<ClassEntity> classEntityList = page2.getRecords();
        long total = page2.getTotal();
        long pages = page2.getPages();
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String, Object>> classList = new ArrayList<>();
        for (ClassEntity classEntity : classEntityList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", classEntity.getId());
            map.put("className", classEntity.getName());
            map.put("majorId", classEntity.getMajor().getId());
            map.put("majorName", classEntity.getMajor().getName());
            classList.add(map);
        }
        data.put("classList", classList);
        return new Result(1, "成功", data);
    }

    @Override
    @PutMapping
    public Result updateClass(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("class_id");
        String name = (String) map.get("name");
        Integer majorId = (Integer) map.get("major_id");
        if (classService.getById(id) == null) {
            return new Result(2, "id=" + id + "的班级不存在", null);
        }
        UpdateWrapper<ClassEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("name", name);
        wrapper.set("major_id", majorId);
        if (classService.update(wrapper)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }

    @Override
    @DeleteMapping
    public Result deleteClassList(@RequestBody List<Integer> list) {
        for (Integer id : list) {
            if (classService.getById(id) == null) {
                return new Result(2, "id=" + id + "的班级不存在", null);
            }
        }
        if (classService.removeByIds(list)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }
}
