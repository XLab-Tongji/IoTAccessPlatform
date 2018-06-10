package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.UserMapper;
import com.lab409.socket.demoServer.netty.handler.SocketServerHandler;
import com.lab409.socket.demoServer.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    @Qualifier("clientNum")
    Integer clientNum;

    @Autowired
    @Qualifier("socketServerHandler")
    SocketServerHandler socketServerHandler;

    private List<Sensor> list = new ArrayList<>();


    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        List<Sensor> sensorList = new ArrayList<>();
        for (Integer i = 0; i < clientNum; i++) {

        }
        modelAndView.addObject("sensorList", sensorList);
        return modelAndView;
    }

    @PostMapping("/refresh/{refreshed}")
    public void doRefresh(@PathVariable int refreshed) {
        this.refreshed = refreshed;
    }

    @GetMapping("/getChangedSocketClient")
    public @ResponseBody
    List<Sensor> getChangedSocketClient() throws Exception {
        list.clear();
        List<Sensor> tmp = socketServerHandler.getSensorList();
        while(tmp.isEmpty()) {
            Thread.sleep(300);
        }
        socketServerHandler.reverseSensorHolder();
        list.addAll(tmp);
        tmp.clear();
        return list;
    }

    private Integer refreshed = 0;
}
