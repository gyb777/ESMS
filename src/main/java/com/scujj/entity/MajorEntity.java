package com.scujj.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_major")
public class MajorEntity {
    private Integer id;
    private String name;
    @TableField(exist = false)
    private CollegeEntity college;
}
