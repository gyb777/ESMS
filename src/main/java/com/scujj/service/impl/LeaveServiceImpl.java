package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.LeaveEntity;
import com.scujj.mapper.LeaveMapper;
import com.scujj.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, LeaveEntity> implements LeaveService {
    @Autowired
    LeaveMapper leaveMapper;

    @Override
    public boolean addOne(Integer studentId, Date startTime, Date endTime, String reason, String imgSrc, String province, String city, String county, String position) {
        return leaveMapper.insertOne(studentId, startTime, endTime, reason, imgSrc, province, city, county, position, new Date()) > 0;
    }

    @Override
    public HashMap<String, Object> getLeaveList(Page<LeaveEntity> myPage,
                                                Date startTime,
                                                Date endTime,
                                                List<Integer> collegeIdList,
                                                List<Integer> majorIdList,
                                                List<Integer> classIdList,
                                                Integer status) {
        long current = myPage.getCurrent();
        long size = myPage.getSize();
        List<LeaveEntity> leaveEntityList = leaveMapper.selectWithStudent((current - 1) * size,
                size, startTime, endTime, collegeIdList, majorIdList, classIdList, status);
        long total = leaveMapper.countWithStudent(startTime, endTime, collegeIdList, majorIdList, classIdList, status);
        long pages = total / size + 1;
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String,Object>> list = new ArrayList<>();
        for (LeaveEntity leave :leaveEntityList) {
            HashMap<String,Object> map = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String start = dateFormat.format(leave.getStartTime());
            String end = dateFormat.format(leave.getEndTime());
            String time = dateFormat.format(leave.getTime());
            map.put("id",leave.getId());
            map.put("studentId",leave.getStudent().getId());
            map.put("studentName",leave.getStudent().getName());
            map.put("startTime",start);
            map.put("endTime",end);
            map.put("reason",leave.getReason());
            map.put("imgSrc",leave.getImgSrc());
            map.put("province",leave.getProvince());
            map.put("city",leave.getCity());
            map.put("county",leave.getCounty());
            map.put("position",leave.getPosition());
            map.put("status",leave.getStatus());
            map.put("rootName",leave.getRootName());
            map.put("remark",leave.getRemark());
            map.put("time",time);
            list.add(map);
        }
        data.put("leaveList", list);
        return data;
    }
}
