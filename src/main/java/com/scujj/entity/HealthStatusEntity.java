package com.scujj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_healthstatus")
public class HealthStatusEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private Integer healthStatus;
    private Integer fever;
    private String others;
    private Integer contact;
    private String riskArea;
    private String province;
    private String city;
    private String county;
    private String position;
    private Date time;
    @TableField(exist = false)
    private StudentEntity student;
}
