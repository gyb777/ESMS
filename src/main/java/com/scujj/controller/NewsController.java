package com.scujj.controller;

import com.scujj.entity.NewsEntity;
import com.scujj.entity.Result;

import java.util.HashMap;
import java.util.List;

public interface NewsController {
    Result addOrUpdateNews(NewsEntity newsEntity);

    Result deleteNewsList(List<Integer> newsIdList);

    Result getNewsList(Long page, Long limit);
}
