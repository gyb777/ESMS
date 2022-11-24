package com.scujj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scujj.entity.LeaveEntity;

import java.util.Date;

public interface LeaveService extends IService<LeaveEntity> {
    boolean addOne(Integer studentId,
                   Date startTime,
                   Date endTime,
                   String reason,
                   String imgSrc,
                   String province,
                   String city,
                   String county,
                   String position);
}
