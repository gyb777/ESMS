package com.scujj.API;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scujj.entity.RiskAreaEntity;
import com.scujj.mapper.RiskAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class UpdateRiskArea {
    @Autowired
    RiskAreaMapper riskAreaMapper;


    public String getJosnString() {
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
//                System.out.println("result:" + resultBuffer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBuffer.toString();
    }

    @Transactional
    public int updateDataBase() {
        System.out.println("开始更新风险地区");
        riskAreaMapper.delete(new QueryWrapper<>());
        int resultCount = 0;
        String josnString = this.getJosnString();
        JSONObject jsonObject = JSONObject.parseObject(josnString);
        String reason = jsonObject.getString("reason");
        if (!reason.equals("success!")) {
            System.out.println("发起API请求失败,错误代码+" + reason);
            return resultCount;
        } else {
            System.out.println("发送API请求成功");
        }
        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray high_list = result.getJSONArray("high_list");
        JSONArray low_list = result.getJSONArray("low_list");
        for (int i = 0; i < high_list.size(); i++) {
            JSONObject areaObj = high_list.getJSONObject(i);
            String province = areaObj.getString("province");
            String city = areaObj.getString("city");
            String county = areaObj.getString("county");
            JSONArray communitys = areaObj.getJSONArray("communitys");
            for (int j = 0; j < communitys.size(); j++) {
                String community = communitys.getString(j);
                RiskAreaEntity riskAreaEntity = new RiskAreaEntity();
                riskAreaEntity.setCity(city);
                riskAreaEntity.setCounty(county);
                riskAreaEntity.setGrade(2);
                riskAreaEntity.setLocation(community);
                riskAreaEntity.setProvince(province);
                resultCount += riskAreaMapper.insertArea(riskAreaEntity);
            }
        }
        for (int i = 0; i < low_list.size(); i++) {
            JSONObject areaObj = low_list.getJSONObject(i);
            String province = areaObj.getString("province");
            String city = areaObj.getString("city");
            String county = areaObj.getString("county");
            JSONArray communitys = areaObj.getJSONArray("communitys");
            for (int j = 0; j < communitys.size(); j++) {
                String community = communitys.getString(j);
                RiskAreaEntity riskAreaEntity = new RiskAreaEntity();
                riskAreaEntity.setCity(city);
                riskAreaEntity.setCounty(county);
                riskAreaEntity.setGrade(1);
                riskAreaEntity.setLocation(community);
                riskAreaEntity.setProvince(province);
                resultCount += riskAreaMapper.insertArea(riskAreaEntity);
            }
        }
        System.out.println("数据更新完毕,已更新" + resultCount + "条数据");
        return resultCount;
    }
}
