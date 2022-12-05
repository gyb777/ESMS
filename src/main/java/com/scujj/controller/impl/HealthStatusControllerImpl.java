package com.scujj.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.HealthStatusController;
import com.scujj.entity.HealthStatusEntity;
import com.scujj.entity.Result;
import com.scujj.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class HealthStatusControllerImpl implements HealthStatusController {
    @Autowired
    HealthStatusService healthStatusService;

    @Override
    @RequestMapping(value = "/student/healthstatus", method = {RequestMethod.POST, RequestMethod.PUT})
    public Result addOrUpdateHealthStatus(@RequestBody HashMap<String, Object> map) {
        HealthStatusEntity healthStatusEntity = new HealthStatusEntity();
        healthStatusEntity.setHealthStatus((Integer) map.get("health_status"));
        healthStatusEntity.setFever((Integer) map.get("fever"));
        healthStatusEntity.setOthers((String) map.get("others"));
        healthStatusEntity.setContact((Integer) map.get("contact"));
        healthStatusEntity.setRiskArea((String) map.get("riskeare"));
        healthStatusEntity.setProvince((String) map.get("province"));
        healthStatusEntity.setCity((String) map.get("city"));
        healthStatusEntity.setCounty((String) map.get("county"));
        healthStatusEntity.setPosition((String) map.get("position"));
        healthStatusEntity.setTime(new Date());
        if (map.get("id") != null) {
            healthStatusEntity.setId((Integer) map.get("id"));
            if (healthStatusService.updateById(healthStatusEntity)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }
        } else {
            healthStatusEntity.setStudentId((Integer) map.get("user_id"));
            if (healthStatusService.save(healthStatusEntity)) {
                return new Result(1, "成功", null);
            } else {
                return new Result(3, "参数错误", null);
            }
        }
    }

    @Override
    @GetMapping("/student/healthstatus")
    public Result selectByStudentId(@RequestParam("userid") Integer studentId,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                                    @RequestParam(value = "start_time", required = false) String startTime,
                                    @RequestParam(value = "end_time", required = false) String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            if (startTime != null) {
                start = dateFormat.parse(startTime);
            }
            if (endTime != null) {
                end = dateFormat.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Result(1, "成功", healthStatusService.getHealthList(studentId, new Page<>(page, limit), start, end));
    }

    @Override
    @GetMapping("/root/healthstatus")
    public Result selectByAdmin(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                                @RequestParam(value = "time", required = false) String time,
                                @RequestParam(value = "college_id_list", required = false) List<Integer> collegeIdList,
                                @RequestParam(value = "major_id_list", required = false) List<Integer> majorIdList,
                                @RequestParam(value = "class_id_list", required = false) List<Integer> classIdList,
                                @RequestParam(value = "is_clock", required = false) Boolean isClock,
                                @RequestParam(value = "is_exception", required = false) Boolean isException) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            if (time != null) {
                dateTime = dateFormat.parse(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Result(1, "成功", healthStatusService.getHealthList(new Page<>(page, limit), dateTime, collegeIdList, majorIdList, classIdList, isClock, isException));
    }

    @Override
    @GetMapping("/root/hasriskarea")
    public Result selectHasRiskArea(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                    @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                                    @RequestParam(value = "start_time", required = false) String startTime,
                                    @RequestParam(value = "end_time", required = false) String endTime,
                                    @RequestParam(value = "college_id_list", required = false) List<Integer> collegeIdList,
                                    @RequestParam(value = "major_id_list", required = false) List<Integer> majorIdList,
                                    @RequestParam(value = "class_id_list", required = false) List<Integer> classIdList,
                                    @RequestParam(value = "range", required = false, defaultValue = "3") Integer range) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            if (startTime != null) {
                start = dateFormat.parse(startTime);
            }
            if (endTime != null) {
                end = dateFormat.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Result(1, "成功", healthStatusService.getHasRiskArea(new Page<>(page, limit), start, end, collegeIdList, majorIdList, classIdList, range));
    }
}
