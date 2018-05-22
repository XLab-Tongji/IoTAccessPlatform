package com.lab409.socket.demoServer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public @ResponseBody
    String redisTest() {
        stringRedisTemplate.opsForValue().set("k5","Spring boot redis");
        return stringRedisTemplate.opsForValue().get("k5");
    }

    @GetMapping("/login/{U}/{P}")
    public void login(@PathVariable("U") String id, @PathVariable("P") String password) {

    }
}
