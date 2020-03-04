package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "/redis", tags = "redis存取对象接口")
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "向Redis中存入对象", notes = "key 键 value 值")
    @PostMapping("/set")
    public boolean redisset(String key, String value){
        User userEntity =new User();
        userEntity.setId(Long.valueOf(1));
        userEntity.setUsername("zhangsan");
        userEntity.setRealname("张三");


        return redisUtil.set(key,userEntity,ExpireTime);

       // return redisUtil.set(key,value);
    }

    @ApiOperation(value = "从Redis中获取对象", notes = "key 键 ")
    @PostMapping("/get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @ApiOperation(value = "检查Redis中存储的值是否过期", notes = "key 键")
    @PostMapping("/expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }


}
