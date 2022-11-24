package com.scujj.controller;

import com.scujj.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface StudentController {
    Result insertStudentList(List<HashMap<String, Object>> studentList);

    Result removeStudentList(List<Integer> studentIdList);

    Result updateStudent(HashMap<String, Object> map);

    Result getStudentList(Long page,
                          Long limit,
                          Integer college_id,
                          Integer major_id,
                          Integer class_id,
                          String key);

    Result getCheckCode(String tele);

    Result phoneNumberLogin(HashMap<String,String> map);

    Result getUser(HttpServletRequest httpServletRequest);

    Result login(HashMap<String,String> map);

    Result logout(HttpServletRequest httpServletRequest);
}
