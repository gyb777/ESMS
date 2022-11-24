package com.scujj;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class HttpTest1 {
    @Test
    public void test() {
        HttpURLConnection con = null;
 
        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;
 
        try {
            URL url = new URL("http://apis.juhe.cn/springTravel/risk?key=01a1a20385020310325cf1335f09c1b8");
            //得到连接对象
            con = (HttpURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod("GET");
            //设置Content-Type，此处根据实际情况确定
            con.setRequestProperty("Content-Type", "application/json");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                System.out.println("result:" + resultBuffer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}