package com.scujj.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_class")
public class ClassEntity {
    private Integer id;
    private String name;
    @TableField(exist = false)
    private MajorEntity major;
}
