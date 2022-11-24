package com.scujj.controller;

import com.scujj.entity.Result;
import com.scujj.entity.RootEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface RootController {
    Result login(HashMap<String, Object> map);

    Result logout(HttpServletRequest request);

    Result addRoot(List<HashMap<String, Object>> list);

    Result removeRoot(List<Integer> list);

    Result updateRoot(RootEntity rootEntity);

    Result getRootList(Long page, Long limit, String name, String likeName, String likeJob);
}
