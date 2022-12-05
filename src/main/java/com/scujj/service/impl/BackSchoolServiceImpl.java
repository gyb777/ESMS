package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.BackSchoolEntity;
import com.scujj.entity.LeaveEntity;
import com.scujj.mapper.BackSchoolMapper;
import com.scujj.service.BackSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BackSchoolServiceImpl extends ServiceImpl<BackSchoolMapper,BackSchoolEntity> implements BackSchoolService{
    @Autowired
    BackSchoolMapper backSchoolMapper;
    @Override
    public boolean addOne(Integer studentId, Date endTime, String reason, String img, Integer memberNumber, String member) {
        return backSchoolMapper.insertOne(studentId,endTime,reason,img,memberNumber,member,new Date())>0;
    }

    @Override
    public HashMap<String, Object> getBackSchoolList(Page<LeaveEntity> myPage, Date startTime, Date endTime, List<Integer> collegeIdList, List<Integer> majorIdList, List<Integer> classIdList, Integer status) {
        long current = myPage.getCurrent();
        long size = myPage.getSize();
        List<BackSchoolEntity> BackSchoolEntityList = backSchoolMapper.selectWithStudent((current - 1) * size,
                size, startTime, endTime, collegeIdList, majorIdList, classIdList, status);
        long total = backSchoolMapper.countWithStudent(startTime, endTime, collegeIdList, majorIdList, classIdList, status);
        long pages = total / size + 1;
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", pages);
        data.put("total", total);
        List<HashMap<String,Object>> list = new ArrayList<>();
        for (BackSchoolEntity backSchool :BackSchoolEntityList) {
            HashMap<String,Object> map = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String end = dateFormat.format(backSchool.getEndTime());
            String time = dateFormat.format(backSchool.getTime());
            map.put("id",backSchool.getId());
            map.put("studentId",backSchool.getStudent().getId());
            map.put("studentName",backSchool.getStudent().getName());
            map.put("endTime",end);
            map.put("reason",backSchool.getReason());
            map.put("imgSrc",backSchool.getImg());
            map.put("status",backSchool.getStatus());
            map.put("rootName",backSchool.getRootName());
            map.put("remark",backSchool.getRemark());
            map.put("time",time);
            map.put("member",backSchool.getMember());
            map.put("member_number",backSchool.getMemberNumber());
            list.add(map);
        }
        data.put("leaveList", list);
        return data;
    }
}
