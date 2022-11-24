package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.MajorController;
import com.scujj.entity.MajorEntity;
import com.scujj.entity.Result;
import com.scujj.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/root/major")
public class MajorControllerImpl implements MajorController {
    @Autowired
    MajorService majorService;

    @Override
    @PostMapping
    public Result addMajor(@RequestBody HashMap<String, Object> map) {
        String name = (String) map.get("name");
        Integer collegeId = (Integer) map.get("college_id");
        QueryWrapper<MajorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (majorService.getOne(wrapper) != null) {
            return new Result(2, "专业已存在", null);
        } else {
            if (majorService.insertMajor(name, collegeId)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "未知错误", null);
            }
        }
    }

    @Override
    @GetMapping
    public Result getMajorList(@RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                               @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                               @RequestParam(value = "college_id_list", required = false) List<Integer> idList,
                               @RequestParam(value = "key", required = false) String key) {
        HashMap<String, Object> data = new HashMap<>();
        if (idList == null) {
            idList = new ArrayList<>();
        }
        Page<MajorEntity> page1 = majorService.pageWithCollege(page, limit, idList, key);
        data.put("pages", page1.getPages());
        data.put("total", page1.getTotal());
        List<MajorEntity> list = page1.getRecords();
        List<HashMap<String, Object>> majorList = new ArrayList<>();
        for (MajorEntity major : list) {
            HashMap one = new HashMap();
            one.put("id", major.getId());
            one.put("name", major.getName());
            one.put("collegeId", major.getCollege().getId());
            one.put("collegeName", major.getCollege().getName());
            majorList.add(one);
        }
        data.put("majorList", majorList);
        return new Result(1, "成功", data);
    }

    @Override
    @DeleteMapping
    @Transactional
    public Result deleteMajor(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            if (majorService.getById(id) == null) {
                return new Result(2, "id=" + id + "的数据不存在", null);
            }
        }
        if (majorService.deleteMajor(ids)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }

    @Override
    @PutMapping
    public Result updataMajor(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("id");
        String name = (String) map.get("name");
        Integer collegeId = (Integer) map.get("college_id");
        if (majorService.getById(id) == null) {
            return new Result(2, "专业不存在", null);
        }
        UpdateWrapper<MajorEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("name", name);
        wrapper.set("college_id", collegeId);
        if (majorService.update(wrapper)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }
}
