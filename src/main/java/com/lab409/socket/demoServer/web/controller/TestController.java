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
    @Value("${com.huzehao.name}")
    String name;

    @Value("${com.huzehao.age}")
    int age;

    @Autowired
    UserMapper userMapper;

    @Autowired
    @Qualifier("clientNum")
    Integer clientNum;

    @Autowired
    @Qualifier("socketServerHandler")
    SocketServerHandler socketServerHandler;

    private List<Sensor> list = new ArrayList<>();


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return name + age;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        List<Sensor> sensorList = new ArrayList<>();
        for (Integer i = 0; i < clientNum; i++) {
            //Sensor sensor = new Sensor
              //      (new Long(i), new Long(1),SensorType.valueOf("thunder"), "unknown", "unknown", "unknown", "offline","unknown",new Date());
            //sensorList.add(sensor);
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

    @RequestMapping(value = "/ajax",method = RequestMethod.GET)
    public void ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();

        Random rand = new Random();
        // 死循环 查询有无数据变化
        while (true) {
            Thread.sleep(300); // 休眠300毫秒，模拟处理业务等
            //int i = rand.nextInt(100); // 产生一个0-100之间的随机数
            writer.print("asdfasdfasdf");
            return;
        }
    }

    private Integer refreshed = 0;
}
