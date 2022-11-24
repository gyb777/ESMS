package com.scujj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.scujj.mapper")
@EnableTransactionManagement
@EnableCaching
public class EsmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsmsApplication.class, args);
    }
}
