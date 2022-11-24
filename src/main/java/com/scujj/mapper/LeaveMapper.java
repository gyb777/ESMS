package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.LeaveEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface LeaveMapper extends BaseMapper<LeaveEntity> {
    @Insert("INSERT INTO tb_leave(student_id, start_time, end_time, reason, img_src, province, city, county, position, time) " +
            "VALUES(#{studentId},#{startTime},#{endTime},#{reason},#{imgSrc},#{province},#{city},#{county},#{position},#{time}) ")
    int insertOne(@Param("studentId") Integer studentId,
                  @Param("startTime") Date startTime,
                  @Param("endTime") Date endTime,
                  @Param("reason") String reason,
                  @Param("imgSrc") String imgSrc,
                  @Param("province") String province,
                  @Param("city") String city,
                  @Param("county") String county,
                  @Param("position") String position,
                  @Param("time") Date time);
}
