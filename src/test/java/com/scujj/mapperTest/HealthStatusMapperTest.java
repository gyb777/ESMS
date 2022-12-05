package com.scujj.mapperTest;

import com.scujj.entity.HealthStatusEntity;
import com.scujj.mapper.HealthStatusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class HealthStatusMapperTest {
    @Autowired
    HealthStatusMapper healthStatusMapper;

    @Test
    public void test() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = "2019-10-22";
        String d2 = "2023-12-11";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = dateFormat.parse(d1);
            date2 = dateFormat.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(healthStatusMapper.selectWithStudent(0L, 2L, 10, date1, date2));
    }

    @Test
    public void test2() {
        List<HealthStatusEntity> list = healthStatusMapper.selectByAdmin(0L, 99999L, null, null, null, null,true,false);
        System.out.println(list);
        for (HealthStatusEntity h : list) {
            System.out.println(h);
        }
    }
    @Test
    public void test3(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = "2019-10-22";
        String d2 = "2023-12-11";
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = dateFormat.parse(d1);
            date2 = dateFormat.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<HealthStatusEntity> list =healthStatusMapper.selectHasRiskArea(0L,999L,date1,date2,null,null,null,1);
        System.out.println(healthStatusMapper.countHasRiskArea(date1,date2,null,null,null,1));
        for (HealthStatusEntity h : list) {
            System.out.println(h);
        }
    }
}
