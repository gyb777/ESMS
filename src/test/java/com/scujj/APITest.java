package com.scujj;

import com.scujj.API.UpdateRiskArea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class APITest {
    @Autowired
    UpdateRiskArea updateRiskArea;
    @Test
    public void test(){
        System.out.println(updateRiskArea.updateDataBase());
    }
}
