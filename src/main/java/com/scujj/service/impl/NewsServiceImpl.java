package com.scujj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scujj.entity.NewsEntity;
import com.scujj.mapper.NewsMapper;
import com.scujj.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NewsEntity> implements NewsService {
}
