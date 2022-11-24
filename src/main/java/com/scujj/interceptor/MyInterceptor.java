package com.scujj.interceptor;

import com.alibaba.fastjson.JSON;
import com.scujj.entity.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


public class MyInterceptor implements HandlerInterceptor {


    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        response.setContentType("text/html;charset=UTF-8");
        String token = request.getHeader("token");
        Result result = new Result(0, "token认证失败", null);
        if (null == token) {
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        //获取请求URL
        StringBuffer urls = request.getRequestURL();
        String url = urls.toString();
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        String tokenString = (String) valueOperations.get(token);
        if (url.contains("root")) {
            if (null != tokenString && "root".equals(tokenString)) {
                valueOperations.set(token, tokenString, 5, TimeUnit.MINUTES);
                return true;
            } else {
                response.getWriter().write(JSON.toJSONString(result));
                return false;
            }
        } else {
            if (null != tokenString) {
                valueOperations.set(token, tokenString, 5, TimeUnit.MINUTES);
                return true;
            } else {
                response.getWriter().write(JSON.toJSONString(result));
                return false;
            }
        }
    }
}
