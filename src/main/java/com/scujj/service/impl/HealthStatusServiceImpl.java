package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.HealthStatusEntity;
import com.scujj.entity.LeaveEntity;
import com.scujj.mapper.HealthStatusMapper;
import com.scujj.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class HealthStatusServiceImpl extends ServiceImpl<HealthStatusMapper, HealthStatusEntity> implements HealthStatusService {
    @Autowired
    HealthStatusMapper healthStatusMapper;

    @Override
    public HashMap<String, Object> getHealthList(Integer studentId, Page<HealthStatusEntity> myPage, Date startTime, Date endTime) {
        long current = myPage.getCurrent();
        long size = myPage.getSize();
        List<HealthStatusEntity> healthStatusEntityList = healthStatusMapper.selectWithStudent((current - 1) * size, size, studentId, startTime, endTime);
        long total = healthStatusMapper.CountSelectWithStudent(studentId, startTime, endTime);
        long pages = total / size + 1;
        HashMap<String, Object> data = this.getData(healthStatusEntityList, pages, total);
        return data;
    }

    @Override
    public HashMap<String, Object> getHealthList(Page<LeaveEntity> myPage, Date time, List<Integer> collegeIdList, List<Integer> majorIdList, List<Integer> classIdList, Boolean isClock, Boolean isException) {
        long current = myPage.getCurrent();
        long size = myPage.getSize();
        List<HealthStatusEntity> healthStatusEntityList = healthStatusMapper.selectByAdmin((current - 1) * size, size, time, collegeIdList, majorIdList, classIdList, isClock, isException);
        long total = healthStatusMapper.countSelectByAdmin(time, collegeIdList, majorIdList, classIdList, isClock, isException);
        long pages = total / size + 1;
        return this.getData(healthStatusEntityList, pages, total);
    }

    @Override
    public HashMap<String, Object> getHasRiskArea(Page<LeaveEntity> myPage, Date startTime, Date endTime, List<Integer> collegeIdList, List<Integer> majorIdList, List<Integer> classIdList, Integer range) {
        long current = myPage.getCurrent();
        long size = myPage.getSize();
        List<HealthStatusEntity> healthStatusEntityList = healthStatusMapper.selectHasRiskArea((current - 1) * size, size, startTime, endTime, collegeIdList, majorIdList, classIdList, range);
        long total = healthStatusMapper.countHasRiskArea(startTime, endTime, collegeIdList, majorIdList, classIdList, range);
        long pages = total / size + 1;
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (HealthStatusEntity healthStatus : healthStatusEntityList) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("studentId", healthStatus.getStudent().getId());
            hashMap.put("name", healthStatus.getStudent().getName());
            hashMap.put("classId", healthStatus.getStudent().getClassEntity().getId());
            hashMap.put("className", healthStatus.getStudent().getClassEntity().getName());
            hashMap.put("studentNumber", healthStatus.getStudent().getStudentNumber());
            hashMap.put("phoneNumber", healthStatus.getStudent().getPhoneNumber());
            hashMap.put("inSchool", healthStatus.getStudent().getInSchool());
            hashMap.put("healthStatus", healthStatus.getStudent().getHealthStatus());
            hashMap.put("province",healthStatus.getProvince());
            hashMap.put("city",healthStatus.getCity());
            hashMap.put("county",healthStatus.getCounty());
            hashMap.put("position",healthStatus.getPosition());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String time = dateFormat.format(healthStatus.getTime());
            hashMap.put("time", time);
            list.add(hashMap);
        }
        data.put("studentList",list);
        return data;
    }

    private HashMap<String, Object> getData(List<HealthStatusEntity> healthStatusEntityList, Long pages, Long total) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (HealthStatusEntity healthStatus : healthStatusEntityList) {
            HashMap<String, Object> hashMap = new HashMap<>();
            List<String> exception = new ArrayList<>();
            hashMap.put("id", healthStatus.getId());
            hashMap.put("studentId", healthStatus.getStudentId());
            hashMap.put("studentNumber", healthStatus.getStudent().getStudentNumber());
            hashMap.put("studentName",healthStatus.getStudent().getName());
            hashMap.put("classId", healthStatus.getStudent().getClassEntity().getId());
            hashMap.put("className", healthStatus.getStudent().getClassEntity().getName());
            hashMap.put("province", healthStatus.getProvince());
            hashMap.put("city", healthStatus.getCity());
            hashMap.put("county", healthStatus.getCounty());
            hashMap.put("position", healthStatus.getPosition());
            if (healthStatus.getId() == null) {
                hashMap.put("isClock", false);
            } else {
                hashMap.put("isClock", true);
            }
            if (healthStatus.getTime() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                hashMap.put("time", dateFormat.format(healthStatus.getTime()));
            } else {
                hashMap.put("time", null);
            }
            if (healthStatus.getHealthStatus() != null && healthStatus.getHealthStatus() != 1) {
                exception.add("healthStatus");
            }
            hashMap.put("healthStatus", healthStatus.getHealthStatus());
            if (healthStatus.getFever() != null && healthStatus.getFever() != 1) {
                exception.add("fever");
            }
            hashMap.put("fever", healthStatus.getFever());
            if (healthStatus.getOthers() != null) {
                exception.add("others");
            }
            hashMap.put("others", healthStatus.getOthers());
            if (healthStatus.getContact() != null && healthStatus.getContact() != 1) {
                exception.add("contact");
            }
            hashMap.put("contact", healthStatus.getContact());
            if (healthStatus.getRiskArea() != null) {
                exception.add("riskeara");
            }
            hashMap.put("riskeara", healthStatus.getRiskArea());
            hashMap.put("exception", exception);
            list.add(hashMap);
        }
        data.put("resultList", list);
        return data;
    }
}
