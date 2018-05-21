package com.lab409.socket.demoServer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoginController {
    @GetMapping("/login/{U}/{P}")
    public void login(@PathVariable("U") String id, @PathVariable("P") String password) {

    }
}
