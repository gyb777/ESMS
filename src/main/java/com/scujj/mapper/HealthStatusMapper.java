package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.HealthStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface HealthStatusMapper extends BaseMapper<HealthStatusEntity> {
    List<HealthStatusEntity> selectWithStudent(@Param("page") Long page,
                                               @Param("limit") Long limit,
                                               @Param("studentId") Integer studentId,
                                               @Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime);

    Long CountSelectWithStudent(@Param("studentId") Integer studentId,
                                @Param("startTime") Date startTime,
                                @Param("endTime") Date endTime);

    List<HealthStatusEntity> selectByAdmin(@Param("page") Long page,
                                           @Param("limit") Long limit,
                                           @Param("time") Date time,
                                           @Param("collegeIdList") List<Integer> collegeIdList,
                                           @Param("majorIdList") List<Integer> majorIdList,
                                           @Param("classIdList") List<Integer> classIdList,
                                           @Param("isClock") Boolean isClock,
                                           @Param("isException") Boolean isException);

    Long countSelectByAdmin( @Param("time") Date time,
                             @Param("collegeIdList") List<Integer> collegeIdList,
                             @Param("majorIdList") List<Integer> majorIdList,
                             @Param("classIdList") List<Integer> classIdList,
                             @Param("isClock") Boolean isClock,
                             @Param("isException") Boolean isException);

    List<HealthStatusEntity> selectHasRiskArea(@Param("page") Long page,
                                               @Param("limit") Long limit,
                                               @Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime,
                                               @Param("collegeIdList") List<Integer> collegeIdList,
                                               @Param("majorIdList") List<Integer> majorIdList,
                                               @Param("classIdList") List<Integer> classIdList,
                                               @Param("range") Integer range);

    Long countHasRiskArea(@Param("startTime") Date startTime,
                          @Param("endTime") Date endTime,
                          @Param("collegeIdList") List<Integer> collegeIdList,
                          @Param("majorIdList") List<Integer> majorIdList,
                          @Param("classIdList") List<Integer> classIdList,
                          @Param("range") Integer range);
}
