package com.scujj.controller;

import com.scujj.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface LeaveController {
    Result addOrUpdateLeave(Integer studentId,
                            String start,
                            String end,
                            String reason,
                            String province,
                            String city,
                            String county,
                            String position,
                            MultipartFile img,
                            Integer id);

    Result removeLeave(HashMap<String, Integer> map);


    Result getLeaveList(Integer studentId,
                        Long page,
                        Long limit,
                        Integer status);

    Result getLeaveList(Long page,
                        Long limit,
                        String startTime,
                        String endTime,
                        List<Integer> collegeIdList,
                        List<Integer> majorIdList,
                        List<Integer> classIdList,
                        Integer status);

    Result updateLeave(HashMap<String,Object> map);
}
