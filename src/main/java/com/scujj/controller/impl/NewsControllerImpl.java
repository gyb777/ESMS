package com.scujj.controller.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scujj.controller.NewsController;
import com.scujj.entity.NewsEntity;
import com.scujj.entity.Result;
import com.scujj.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class NewsControllerImpl implements NewsController {
    @Autowired
    NewsService newsService;

    @Override
    @RequestMapping(value = "/root/news", method = {RequestMethod.POST, RequestMethod.PUT})
    public Result addOrUpdateNews(@RequestBody NewsEntity newsEntity) {
        newsEntity.setTime(new Date());
        if (newsService.saveOrUpdate(newsEntity)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "参数错误", null);
        }
    }

    @Override
    @DeleteMapping("/root/news")
    public Result deleteNewsList(@RequestBody List<Integer> newsIdList) {
        if (newsService.removeByIds(newsIdList)) {
            return new Result(1, "成功", null);
        } else {
            return new Result(3, "参数错误", null);
        }
    }

    @Override
    @GetMapping("/student/news")
    public Result getNewsList(@RequestParam(value = "page", defaultValue = "1", required = false) Long page,
                              @RequestParam(value = "limit", required = false, defaultValue = "99999") Long limit) {
        Page<NewsEntity> page1 = new Page<>(page, limit);
        QueryWrapper<NewsEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("time");
        Page<NewsEntity> page2 = newsService.page(page1);
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", page2.getTotal());
        data.put("pages", page2.getPages());
        List<HashMap<String, Object>> newsList = new ArrayList<>();
        List<NewsEntity> list = page2.getRecords();
        for (NewsEntity news : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", news.getId());
            map.put("title", news.getTitle());
            map.put("context", news.getContext());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            map.put("time", dateFormat.format(news.getTime()));
            newsList.add(map);
        }
        data.put("newsList", newsList);
        return new Result(1, "成功", data);
    }
}
