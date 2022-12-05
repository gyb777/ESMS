package com.scujj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("tb_backschool")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackSchoolEntity {
    private Integer id;
    private Date endTime;
    private String reason;
    private String img;
    private Integer memberNumber;
    private String member;
    @TableField(exist = false)
    private StudentEntity student;
    private Integer status;
    private String rootName;
    private String remark;
    private Date time;
}
