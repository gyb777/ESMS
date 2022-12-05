package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.BackSchoolController;
import com.scujj.entity.BackSchoolEntity;
import com.scujj.entity.Result;
import com.scujj.entity.RootEntity;
import com.scujj.service.BackSchoolService;
import com.scujj.service.RootService;
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
public class BackSchoolControllerImpl implements BackSchoolController {
    private static final String IMG_PATH = "E:\\JaveProject\\ESMS_img\\backschool\\";
    @Autowired
    BackSchoolService backSchoolService;
    @Autowired
    RootService rootService;

    @Override
    @PostMapping("/student/backschool")
    public Result addOrUpdateBackSchool(@RequestParam(value = "backschool_id", required = false) Integer id,
                                        @RequestParam(value = "student_id",required = false) Integer studentId,
                                        @RequestParam("end_time") String end,
                                        @RequestParam("reason") String reason,
                                        @RequestParam("img") MultipartFile img,
                                        @RequestParam("member_number") Integer memberNumber,
                                        @RequestParam(value = "member", required = false) String member) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date endTime = null;
        try {
            endTime = dateFormat.parse(end);
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
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = img.getInputStream();
            outputStream = new FileOutputStream(IMG_PATH + fileName);
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(2, "图片上传失败", null);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (id == null || studentId != null) {
            if (backSchoolService.addOne(studentId, endTime, reason, "/img/backschool/" + fileName, memberNumber, member)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }
        } else {
            UpdateWrapper<BackSchoolEntity> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", id);
            wrapper.set("end_time", endTime);
            wrapper.set("reason", reason);
            wrapper.set("img", "/img/leave/" + fileName);
            wrapper.set("member_number", memberNumber);
            wrapper.set("member", member);
            wrapper.set("time", new Date());
            if (backSchoolService.update(wrapper)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }

        }

    }

    @Override
    @DeleteMapping("/student/backschool")
    public Result removeBackSchool(@RequestBody HashMap<String, Integer> map) {
        Integer id = map.get("backschool_id");
        if (id != null && backSchoolService.removeById(id)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "参数错误", null);
        }
    }

    @Override
    @GetMapping("/student/backschool")
    public Result getBackSchoolList(@RequestParam("student_id") Integer id,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                                    @RequestParam(value = "status", required = false) Integer status) {
        QueryWrapper<BackSchoolEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id", id);
        if (status != null && status != 0) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("time");
        Page<BackSchoolEntity> page1 = new Page<>(page, limit);
        Page<BackSchoolEntity> page2 = backSchoolService.page(page1, wrapper);
        List<BackSchoolEntity> backSchoolEntityList = page2.getRecords();
        long pages = page2.getPages();
        long total = page2.getTotal();
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String, Object>> leaveList = new ArrayList<>();
        for (BackSchoolEntity backSchoolEntity : backSchoolEntityList) {
            HashMap<String, Object> hashMap = new HashMap<>();
            Date end = backSchoolEntity.getEndTime();
            Date timedate = backSchoolEntity.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String endTime = dateFormat.format(end);
            String time = dateFormat.format(timedate);
            hashMap.put("id", backSchoolEntity.getId());
            hashMap.put("studentId", id);
            hashMap.put("end_time", endTime);
            hashMap.put("reason", backSchoolEntity.getReason());
            hashMap.put("imgSrc", backSchoolEntity.getImg());
            hashMap.put("status", backSchoolEntity.getStatus());
            hashMap.put("rootName", backSchoolEntity.getRootName());
            hashMap.put("remark", backSchoolEntity.getRemark());
            hashMap.put("time", time);
            hashMap.put("memberNumber", backSchoolEntity.getMemberNumber());
            hashMap.put("member", backSchoolEntity.getMember());
            leaveList.add(hashMap);
        }
        data.put("leaveList", leaveList);
        return new Result(1, "成功", data);
    }

    @Override
    @PutMapping("/root/backschool")
    public Result updateBackSchool(@RequestBody HashMap<String, Object> map) {
        Integer rootId = (Integer) map.get("root_id");
        Integer BackSchoolId = (Integer) map.get("backschool_id");
        Integer status = (Integer) map.get("status");
        String remark = (String) map.get("remark");
        if (rootId == null || BackSchoolId == null || status == null || remark == null || remark.equals("")) {
            return new Result(3, "参数错误", null);
        }
        RootEntity rootEntity = rootService.getById(rootId);
        if (rootEntity == null) {
            return new Result(3, "参数错误", null);
        }
        UpdateWrapper<BackSchoolEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", BackSchoolId);
        wrapper.set("status", status);
        wrapper.set("remark", remark);
        wrapper.set("root_name", rootEntity.getName());
        wrapper.set("time", new Date());
        if (backSchoolService.update(wrapper)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "参数错误", null);
        }
    }

    @Override
    @GetMapping("/root/backschool")
    public Result getBackSchoolList(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "9999") Long limit,
                                    @RequestParam(value = "start_time", required = false) String startTime,
                                    @RequestParam(value = "end_time", required = false) String endTime,
                                    @RequestParam(value = "college_id_list", required = false) List<Integer> collegeIdList,
                                    @RequestParam(value = "major_id_list", required = false) List<Integer> majorIdList,
                                    @RequestParam(value = "class_id_list", required = false) List<Integer> classIdList,
                                    @RequestParam(value = "status", required = false) Integer status) {
        Date start = null;
        Date end = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startTime != null) {
                start = dateFormat.parse(startTime);
            }
            if (endTime != null) {
                end = dateFormat.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return new Result(3, "参数错误", null);
        }
        return new Result(1, "成功", backSchoolService.getBackSchoolList(new Page<>(page, limit), start, end, collegeIdList, majorIdList, classIdList, status));
    }
}
