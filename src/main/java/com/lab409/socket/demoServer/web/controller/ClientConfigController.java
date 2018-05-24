package com.lab409.socket.demoServer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
@RequestMapping("/config")
public class ClientConfigController {

    @GetMapping("/group")
    public ModelAndView clientGroup() {
        return null;
    }
}
