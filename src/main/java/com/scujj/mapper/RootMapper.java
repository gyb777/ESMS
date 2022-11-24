package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.RootEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RootMapper extends BaseMapper<RootEntity> {
    @Insert("INSERT INTO tb_root(name, job, password) VALUES (#{name},#{job},#{password}) ")
    int addOne(RootEntity rootEntity);
}
