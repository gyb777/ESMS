package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.ClassEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ClassMapper extends BaseMapper<ClassEntity> {
    @Insert("INSERT INTO tb_class(name, major_id) VALUES (#{name},#{majorId})")
    int addOne(@Param("name") String name, @Param("majorId") Integer majorId);

    List<ClassEntity> listClassWithMajor(Long page, Long limit, Integer collegeId, Integer majorId, String key);

    long countClassWithMajor(@Param("page") Long page,
                             @Param("limit") Long limit,
                             @Param("collegeId") Integer collegeId,
                             @Param("majorId") Integer majorId,
                             @Param("key") String key);
}
