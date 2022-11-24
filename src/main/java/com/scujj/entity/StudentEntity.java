package com.scujj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("tb_student")
public class StudentEntity {
    private Integer id;
    private String name;
    private String studentNumber;
    private String phoneNumber;
    private String password;
    private Integer inSchool;
    private Integer healthStatus;
    @TableField(exist = false)
    private ClassEntity classEntity;
}
