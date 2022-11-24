package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.entity.Result;
import com.scujj.entity.RootEntity;
import com.scujj.service.RootService;
import com.scujj.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/root")
public class RootController implements com.scujj.controller.RootController {
    @Autowired
    RootService rootService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    @PostMapping("/login")
    public Result login(@RequestBody HashMap<String, Object> map) {
        String name = (String) map.get("name");
        String password = (String) map.get("password");
        QueryWrapper<RootEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("password", password);
        RootEntity rootEntity = rootService.getOne(wrapper);
        if (null != rootEntity) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userId", String.valueOf(rootEntity.getId()));
            hashMap.put("time", String.valueOf(new Date().getTime()));
            String token = JWTUtils.getToken(hashMap);
            stringRedisTemplate.opsForValue().set(token, "root", 5, TimeUnit.MINUTES);
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", rootEntity.getId());
            data.put("name", rootEntity.getName());
            data.put("job", rootEntity.getJob());
            data.put("token", token);
            return new Result(1, "成功", data);
        } else {
            return new Result(2, "用户名或密码错误", null);
        }
    }

    @Override
    @GetMapping("/logout")
    public Result logout(@Autowired HttpServletRequest request) {
        String token = request.getHeader("token");
        stringRedisTemplate.delete(token);
        return new Result(1, "成功", null);
    }

    @Override
    @PostMapping
    @Transactional
    public Result addRoot(@RequestBody List<HashMap<String, Object>> list) {
        for (HashMap<String, Object> map : list) {
            String name = (String) map.get("name");
            String password = (String) map.get("password");
            String job = (String) map.get("job");
            rootService.insert(new RootEntity(null, name, job, password));
        }
        return new Result(1, "成功", null);
    }

    @Override
    @DeleteMapping
    @Transactional
    public Result removeRoot(@RequestBody List<Integer> list) {
        for (Integer id : list) {
            if (rootService.getById(id) == null) {
                return new Result(2, "id=" + id + "的管理员不存在", null);
            }
        }
        if (rootService.removeByIds(list)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "未知错误", null);
        }
    }

    @Override
    @PutMapping
    @Transactional
    public Result updateRoot(@RequestBody RootEntity rootEntity) {
        boolean f = rootService.updateById(rootEntity);
        if (f) {
            return new Result(1, "成功", null);
        } else {
            return new Result(2, "管理员不存在", null);
        }
    }

    @Override
    @GetMapping
    public Result getRootList(@RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                              @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "like_name", required = false) String likeName,
                              @RequestParam(value = "like_job", required = false) String likeJob) {
        QueryWrapper<RootEntity> wrapper = new QueryWrapper<>();
        if (name != null) {
            wrapper.eq("name", name);
        }
        if (likeName != null) {
            wrapper.like("name", likeName);
        }
        if (likeJob != null) {
            wrapper.like("job", likeJob);
        }
        Page<RootEntity> page1 = new Page<>(page, limit);
        Page<RootEntity> page2 = rootService.page(page1, wrapper);
        List<RootEntity> rootEntityList = page2.getRecords();
        HashMap<String, Object> data = new HashMap<>();
        data.put("pages", page2.getPages());
        data.put("total", page2.getTotal());
        data.put("rootList", rootEntityList);
        return new Result(1, "成功", data);
    }
}
