package com.scujj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class EsmsApplicationTests {

    @Test
    void contextLoads() {
        Date date = new Date();
        System.out.println(date);
    }

}
