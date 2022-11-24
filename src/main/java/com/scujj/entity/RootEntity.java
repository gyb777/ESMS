package com.scujj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_root")
public class RootEntity {
    private Integer id;
    private String name;
    private String job;
    private String password;
}
