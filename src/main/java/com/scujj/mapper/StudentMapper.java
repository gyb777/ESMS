package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.StudentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {
    @Insert("INSERT INTO tb_student(name, class_id, student_number, phone_number, password) VALUES (#{name},#{class_id},#{student_number},#{phone_number},#{password})")
    int insertStudent(@Param("name") String name,
                      @Param("class_id") Integer class_id,
                      @Param("student_number") String sutdent_number,
                      @Param("phone_number") String phone_number,
                      @Param("password") String password);
}
