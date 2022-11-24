package com.scujj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scujj.entity.StudentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {
    @Insert("INSERT INTO tb_student(name, class_id, student_number, password) VALUES (#{name},#{class_id},#{student_number},#{password})")
    int insertStudent(@Param("name") String name,
                      @Param("class_id") Integer class_id,
                      @Param("student_number") String sutdent_number,
                      @Param("password") String password);

    List<StudentEntity> listStudentWithClass(@Param("page") Long page,
                                             @Param("limit") Long limit,
                                             @Param("collegeId") Integer collegeId,
                                             @Param("majorId") Integer majorId,
                                             @Param("classId") Integer classId,
                                             @Param("key") String key);

    long countStudentWithClass(@Param("page") Long page,
                              @Param("limit") Long limit,
                              @Param("collegeId") Integer collegeId,
                              @Param("majorId") Integer majorId,
                              @Param("classId") Integer classId,
                              @Param("key") String key);

    long countHealth(@Param("page") Long page,
                    @Param("limit") Long limit,
                    @Param("collegeId") Integer collegeId,
                    @Param("majorId") Integer majorId,
                    @Param("classId") Integer classId,
                    @Param("key") String key);

    long countDiagnosis(@Param("page") Long page,
                       @Param("limit") Long limit,
                       @Param("collegeId") Integer collegeId,
                       @Param("majorId") Integer majorId,
                       @Param("classId") Integer classId,
                       @Param("key") String key);

    long countInSchool(@Param("page") Long page,
                      @Param("limit") Long limit,
                      @Param("collegeId") Integer collegeId,
                      @Param("majorId") Integer majorId,
                      @Param("classId") Integer classId,
                      @Param("key") String key);
}
