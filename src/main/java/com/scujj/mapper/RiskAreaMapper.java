package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.RiskAreaEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiskAreaMapper extends BaseMapper<RiskAreaEntity> {
    @Insert("insert into tb_riskarea(province, city, county, location, grade) values (#{province},#{city},#{county},#{location},#{grade})")
    int insertArea(RiskAreaEntity riskAreaEntity);
}
