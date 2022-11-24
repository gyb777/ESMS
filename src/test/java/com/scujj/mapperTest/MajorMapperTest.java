package com.scujj.mapperTest;

import com.scujj.mapper.MajorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MajorMapperTest {
    @Autowired
    MajorMapper majorMapper;
    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(4);
        System.out.println(majorMapper.listWithCollege(0L, 2L, null, ""));
    }
    @Test
    public void  test2(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        System.out.println(majorMapper.selectCountWithCollege(list,null));
    }
}
