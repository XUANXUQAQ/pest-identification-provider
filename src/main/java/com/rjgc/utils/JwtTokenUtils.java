package com.rjgc.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * @author zhaoyunjie
 * @date 2021-04-15 19:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@Slf4j
public class JwtTokenUtils {

    private String secret;

    private long expire;

    private volatile byte[] base64EncodeSecret = null;

    /**
     * 生成jwt令牌
     *
     * @param username 用户名
     * @return jwt令牌
     */
    public String generateToken(String username) {
        if (base64EncodeSecret == null) {
            base64EncodeSecret = Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8));
        }
        // 初始时间
        Date nowDate = new Date();
        // 过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, base64EncodeSecret)
                .compact();
    }

    /**
     * 解析token数据
     *
     * @param token jwt令牌
     * @return 主体信息
     */
    public Claims getClaimByToken(String token) {
        try {
            if (base64EncodeSecret == null) {
                base64EncodeSecret = Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8));
            }
            return Jwts.parser()
                    .setSigningKey(base64EncodeSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
