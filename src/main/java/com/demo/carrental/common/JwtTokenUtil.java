package com.demo.carrental.common;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.demo.carrental.entity.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private int expire;

    @Value("${jwt.header}")
    public String header;

    private final String signature_pre = "GETeXLklZD1f6e";

    private final String prefix = "token:userId:";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String createToken(Customer customer){
        return createToken(String.valueOf(customer.getId()), customer.getEmail());
    }


    public String createToken(String userId, String userName){
        String token = redisTemplate.opsForValue().get(prefix+userId);
        if (StringUtils.isNotEmpty(token)){
            renewToken(token, String.valueOf(userId), expire);
            return token;
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date nowDate = new Date();

        token = Jwts.builder()
                .setId(userId)
                .setSubject(userName)
                .setIssuedAt(nowDate)
                .signWith(getKey(), signatureAlgorithm)
                .compact();

        redisTemplate.opsForValue().set(token, userId, expire, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(prefix+userId, token, expire, TimeUnit.MINUTES);
        return token;
    }

    public SecretKey getKey(){

        String encodedSecretString = signature_pre + secret;
        byte[] encodedKey = Base64.decodeBase64(encodedSecretString);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    public Claims parseJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    private void renewToken(String token, String userId, long ttl){
        redisTemplate.expire(token, ttl, TimeUnit.MINUTES);
        redisTemplate.expire(prefix+userId, ttl, TimeUnit.MINUTES);
    }

    public boolean checkAndRenewTokenExpiration(String token, String userId){
        Long ttl = redisTemplate.getExpire(token, TimeUnit.MINUTES);
        if (null != ttl && ttl > 0){
            long newTTL = expire - 10L > ttl ? ttl + 10L : expire;
            renewToken(token, userId, newTTL);
            return true;
        }
        return false;
    }
}
