package com.scujj;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.scujj.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.HashMap;

@SpringBootTest
public class JWTTest {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void test(){
        HashMap<String,String> map = new HashMap<>();
        map.put("userId","1");
        map.put("userPower","root");
        String token = JWTUtils.getToken(map);
        System.out.println(token);

    }
    @Test
    public void test2(){
        DecodedJWT decodedJWT = JWTUtils.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxIiwidXNlclBvd2VyIjoicm9vdCJ9.GniUUh7z_fU_mAGTRRQbUSIvQKJUqc_PE4aFSH8z5nQ");
        Claim userId = decodedJWT.getClaim("userId");
        Claim userPower = decodedJWT.getClaim("userPower");
        System.out.println("userid="+userId.asString());
        System.out.println("userPower="+userPower.asString());
    }
    @Test
    public void redisTest(){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("test","root");
    }

    @Test
    public void redisTest1(){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        System.out.println(valueOperations.get("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxIn0.-76vSOhtgU9UKtwa0zZJOl-Azb6Fy1ShgsgPp0QFXEA"));
    }
}
