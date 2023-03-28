package com.kang.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kang
 */
@Component
public class JwtUtil {

    @Value("${jwt.expireTime}")
    private long expire;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.header}")
    private String header;


    /**
     * 生成jwt
     * @return String
     */
    public String generateToken(String userInfo) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * 60 * 60 * 24 * expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userInfo)
                .setIssuedAt(nowDate)
                // 1天過期
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)  // 设置签名 使用HS512算法 并设置SecretKey(字符串)
                .compact();
    }

    /**
     * 解析jwt
     * @return Claims
     */
    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // jwt是否过期
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public String getHeader(){
        return header;
    }
}
