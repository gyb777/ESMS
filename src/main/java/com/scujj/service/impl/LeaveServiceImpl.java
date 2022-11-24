package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.LeaveEntity;
import com.scujj.mapper.LeaveMapper;
import com.scujj.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, LeaveEntity> implements LeaveService {
    @Autowired
    LeaveMapper leaveMapper;

    @Override
    public boolean addOne(Integer studentId, Date startTime, Date endTime, String reason, String imgSrc, String province, String city, String county, String position) {
        return leaveMapper.insertOne(studentId, startTime, endTime, reason, imgSrc, province, city, county, province, new Date()) > 0;
    }
}
