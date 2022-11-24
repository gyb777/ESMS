package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.CollegeController;
import com.scujj.entity.CollegeEntity;
import com.scujj.entity.Result;
import com.scujj.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/root/college")
public class CollegeControllerImpl implements CollegeController {
    @Autowired
    CollegeService collegeService;

    @Override
    @PostMapping
    public Result addCollege(@RequestBody HashMap<String, String> map) {
        QueryWrapper<CollegeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", map.get("name"));
        if (collegeService.getOne(wrapper) == null) {
            if (collegeService.insertCollege(map.get("name"))) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "未知错误", null);
            }
        } else {
            return new Result(2, "名称已存在", null);
        }
    }

    @Override
    @GetMapping
    public Result getCollegeList(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                 @RequestParam(value = "limit", required = false, defaultValue = "0x7fffffffffffffff") Long limit,
                                 @RequestParam(value = "key", required = false) String key) {
        Page<CollegeEntity> page1 = new Page<>(page, limit);
        QueryWrapper<CollegeEntity> wrapper = new QueryWrapper<>();
        if (key != null) {
            wrapper.like("name", key);
        }
        Page<CollegeEntity> page2 = collegeService.page(page1, wrapper);
        List<CollegeEntity> collegeEntities = page2.getRecords();
        long total = page2.getTotal();
        long pages = page2.getPages();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("pages", pages);
        resultMap.put("total", total);
        resultMap.put("collegeList", collegeEntities);
        return new Result(1, "成功", resultMap);
    }

    @Override
    @PutMapping
    public Result updateCollege(@RequestBody CollegeEntity college) {
        QueryWrapper<CollegeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", college.getId());
        if (collegeService.getOne(wrapper) == null) {
            return new Result(2, "学院不存在", null);
        } else {
            if (collegeService.updateById(college)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "未知错误", null);
            }
        }
    }

    @Override
    @DeleteMapping
    @Transactional
    public Result deleteCollegeList(@RequestBody List<Integer> list) {
        for (Integer id : list) {
            if (collegeService.getById(id) == null) {
                return new Result(2, "id=" + id + "的学院不存在", null);
            }
        }
        if (collegeService.removeCollegeByIds(list)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }
}
