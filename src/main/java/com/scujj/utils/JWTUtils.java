package com.scujj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    // 签名密钥
    private static final String SECRET = "!DAR$";

    /**
     * 生成token
     *
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String getToken(Map<String, String> payload) {
        Calendar calendar = Calendar.getInstance();

        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        payload.forEach((k, v) -> builder.withClaim(k, v));
        // 指定过期时间和签名算法
        String token = builder.sign(Algorithm.HMAC256(SECRET));
        return token;
    }


    /**
     * 解析token
     *
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decode(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }
}

