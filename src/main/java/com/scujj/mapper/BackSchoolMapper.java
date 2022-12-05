package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.BackSchoolEntity;
import com.scujj.entity.LeaveEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BackSchoolMapper extends BaseMapper<BackSchoolEntity> {
    @Insert("INSERT INTO tb_backschool(STUDENT_ID, END_TIME, REASON, IMG, MEMBER_NUMBER, MEMBER,TIME) VALUES (#{studentId},#{endTime},#{reason},#{img},#{memberNumber},#{member},#{time})")
    int insertOne(@Param("studentId") Integer studentId,
                  @Param("endTime") Date endTime,
                  @Param("reason") String reason,
                  @Param("img") String img,
                  @Param("memberNumber") Integer memberNumber,
                  @Param("member") String member,
                  @Param("time") Date time);

    List<BackSchoolEntity> selectWithStudent(@Param("page") Long page,
                                        @Param("limit") Long limit,
                                        @Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime,
                                        @Param("collegeIdList") List<Integer> collegeIdList,
                                        @Param("majorIdList") List<Integer> majorIdList,
                                        @Param("classIdList") List<Integer> classIdList,
                                        @Param("status") Integer status);

    Long countWithStudent(@Param("startTime") Date startTime,
                          @Param("endTime") Date endTime,
                          @Param("collegeIdList") List<Integer> collegeIdList,
                          @Param("majorIdList") List<Integer> majorIdList,
                          @Param("classIdList") List<Integer> classIdList,
                          @Param("status") Integer status);
}
