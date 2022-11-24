package com.scujj.mapperTest;


import com.scujj.entity.RiskAreaEntity;
import com.scujj.mapper.RiskAreaMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RiskAreaMapperTest {
    @Autowired
    RiskAreaMapper riskAreaMapper;

    @Test
    public void test(){
        RiskAreaEntity riskAreaEntity = new RiskAreaEntity(1,"四川","眉山","彭山区","四川大学锦江学院",1);
        int count = riskAreaMapper.insert(riskAreaEntity);
        System.out.println(count);
    }
}
