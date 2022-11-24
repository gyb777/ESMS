package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.LeaveController;
import com.scujj.entity.LeaveEntity;
import com.scujj.entity.Result;
import com.scujj.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class LeaveControllerImpl implements LeaveController {
    private static final String IMG_PATH = "E:\\JaveProject\\ESMS_img\\leave\\";
    @Autowired
    LeaveService leaveService;

    @Override
    @PostMapping("/student/leave")
    public Result addOrUpdateLeave(@RequestParam(value = "student_id", required = false) Integer studentId,
                                   @RequestParam("start_time") String start,
                                   @RequestParam("end_time") String end,
                                   @RequestParam("reason") String reason,
                                   @RequestParam("province") String province,
                                   @RequestParam("city") String city,
                                   @RequestParam("county") String county,
                                   @RequestParam("position") String position,
                                   @RequestParam("img") MultipartFile img,
                                   @RequestParam(value = "leave_id", required = false) Integer id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date endTime = null;
        Date startTime = null;
        try {
            endTime = dateFormat.parse(end);
            startTime = dateFormat.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Result(3, "参数错误", null);
        }
        String oldName = img.getOriginalFilename();
        String str = oldName.substring(oldName.lastIndexOf("."));
        if (!(str.contains("jpg") || str.contains("jpeg") || str.contains("png"))) {
            return new Result(3, "图片格式错误", null);
        }
        String fileName = UUID.randomUUID() + str;
        try {
            InputStream inputStream = img.getInputStream();
            OutputStream outputStream = new FileOutputStream(IMG_PATH + fileName);
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(2, "图片上传失败", null);
        }
        if (id == null && studentId != null) {
            if (leaveService.addOne(studentId, startTime, endTime, reason, "/img/leave/" + fileName, province, city, county, position)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }
        } else {
            UpdateWrapper<LeaveEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            updateWrapper.set("start_time", startTime);
            updateWrapper.set("end_time", endTime);
            updateWrapper.set("reason", reason);
            updateWrapper.set("img_src", "/img/leave/" + fileName);
            updateWrapper.set("province", province);
            updateWrapper.set("city", city);
            updateWrapper.set("county", county);
            updateWrapper.set("position", position);
            updateWrapper.set("time", new Date());
            if (leaveService.update(updateWrapper)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }
        }


    }

    @Override
    @DeleteMapping("/student/leave")
    public Result removeLeave(@RequestBody HashMap<String, Integer> map) {
        Integer id = map.get("leave_id");
        if (id == null) {
            return new Result(3, "参数错误", null);
        } else {
            if (leaveService.removeById(id)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }
        }
    }


    @Override
    @GetMapping("/student/leave")
    public Result getLeaveList(@RequestParam("student_id") Integer studentId,
                               @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                               @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                               @RequestParam(value = "status", required = false) Integer status) {
        QueryWrapper<LeaveEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id", studentId);
        if (status != null) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("time");
        Page<LeaveEntity> page1 = new Page<>(page, limit);
        Page<LeaveEntity> myPage = leaveService.page(page1, wrapper);
        List<LeaveEntity> leaveEntityList = myPage.getRecords();
        Long total = myPage.getTotal();
        Long pages = myPage.getPages();
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String, Object>> leaveList = new ArrayList<>();
        for (LeaveEntity leave : leaveEntityList) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Date start = leave.getStartTime();
            Date end = leave.getEndTime();
            Date timedate = leave.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String startTime = dateFormat.format(start);
            String endTime = dateFormat.format(end);
            String time = dateFormat.format(timedate);
            hashMap.put("id", leave.getId());
            hashMap.put("studentId", studentId);
            hashMap.put("startTime", startTime);
            hashMap.put("end_time", endTime);
            hashMap.put("reason", leave.getReason());
            hashMap.put("imgSrc", leave.getImgSrc());
            hashMap.put("province", leave.getProvince());
            hashMap.put("city", leave.getCity());
            hashMap.put("county", leave.getCounty());
            hashMap.put("position", leave.getPosition());
            hashMap.put("status", leave.getStatus());
            hashMap.put("rootName", leave.getRootName());
            hashMap.put("remark", leave.getRemark());
            hashMap.put("time", time);
            leaveList.add(hashMap);
        }
        data.put("leaveList", leaveList);
        return new Result(1, "成功", data);
    }
}