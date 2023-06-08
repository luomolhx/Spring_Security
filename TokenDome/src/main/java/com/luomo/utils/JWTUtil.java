package com.luomo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName: JWTUtil
 * Package: com.luomo.utils
 * Description:
 * JWT工具类
 * @Author: luomo
 * @Create: 2023/6/8 - 15:28
 * @Version: v1.0
 */
public class JWTUtil {
    // 有效期
    public static final Long JWT_TTL = 60 * 60 * 1000L; // 60 * 60 * 1000 = 一个小时
    // 设置秘钥明文
    public static final String JWT_KEY = "luomo";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replace("_", "");
        return token;
    }

    public static String createJWT(String subject, Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID()); // 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) // 唯一ID
                .setSubject(subject) // 主题 可以是JSON数据
                .setIssuer("lm") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 使用H256对称加密 第二个参数是秘钥
                .setExpiration(expDate);
    }

    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWTUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length,  "AES");
        return key;
    }

    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
