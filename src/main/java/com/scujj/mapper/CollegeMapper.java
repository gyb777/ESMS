package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.CollegeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollegeMapper extends BaseMapper<CollegeEntity> {
    @Insert("INSERT INTO tb_college(name) VALUES (#{name})")
    int addOne(String name);

    int removeCollege(List<Integer> ids);
}
