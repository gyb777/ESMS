package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.MajorEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MajorMapper extends BaseMapper<MajorEntity> {
    @Insert("insert into tb_major(name, college_id) VALUES (#{name},#{collegeId})")
    int addOne(@Param("name") String name, @Param("collegeId") Integer collegeId);


    List<MajorEntity> listWithCollege(@Param("page") Long page,
                                      @Param("limit") Long limit,
                                      @Param("collegeIdList") List<Integer> collegeIdList,
                                      @Param("nameKey") String nameKey);

    Integer selectCountWithCollege(@Param("collegeIdList") List<Integer> collegeIdList,
                                   @Param("nameKey") String nameKey);

    int deleteWithCollegeAndClassAndStudent(@Param("ids") List<Integer> ids);
}
