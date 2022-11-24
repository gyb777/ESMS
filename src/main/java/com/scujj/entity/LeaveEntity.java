package com.scujj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_leave")
public class LeaveEntity {
    Integer id;
    Date startTime;
    Date endTime;
    String reason;
    String imgSrc;
    String province;
    String city;
    String county;
    String position;
    Integer status;
    String rootName;
    String remark;
    Date time;
    @TableField(exist = false)
    StudentEntity student;
}
