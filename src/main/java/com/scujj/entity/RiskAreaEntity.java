package com.scujj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_riskarea")
public class RiskAreaEntity {
    @TableId("id")
    private Integer id;
    private String province;
    private String city;
    private String county;
    private String location;
    private Integer grade;
}
