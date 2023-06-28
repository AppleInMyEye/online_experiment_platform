package com.ustc.oep;

import com.alibaba.fastjson.JSON;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;


@SpringBootTest
public class Test {
//    @Autowired
//    RedisTemplate redisTemplate;
//    @Autowired
//    MenuMapper menuMapper;
//    @org.junit.jupiter.api.Test
//    public void test(){
//        System.out.println(redisTemplate.opsForValue().get("login:4"));
//        LoginUser loginUser1 = JSON.parseObject(redisTemplate.opsForValue().get("login:4").toString(), LoginUser.class);
//        ValueOperations<String , LoginUser> valueOperations = redisTemplate.opsForValue();
//        LoginUser loginUser = valueOperations.get("login:4");
//        System.out.println(loginUser);
//        return;
//    }
//
//    @org.junit.jupiter.api.Test
//    public void test2(){
//        List<String> list = menuMapper.selectMenuPermsByUserId(1L);
//        System.out.println(list);
//    }
//
//    @org.junit.jupiter.api.Test
//    public void test3(){
//        System.out.println("≤‚ ‘3");
//    }
    @org.junit.jupiter.api.Test
    public static void main(String[] args) {
        System.out.println("≤‚ ‘3");
    }
}
