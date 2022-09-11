package com.demo.carrental.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@ActiveProfiles("local")
@SpringBootTest
public class TestRedis {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void TextTest(){
        redisTemplate.opsForValue().set("hhh", "1234", 10, TimeUnit.MINUTES);
        String a = redisTemplate.opsForValue().get("hhh");
        p(redisTemplate.getExpire("hhh"));
        assert Objects.equals(a, "1234");
    }

    private void p(Object a){
        System.out.println(a);
    }
}
