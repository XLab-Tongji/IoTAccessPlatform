package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorMsg;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RestController
@EnableScheduling
public class WebSocketController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;


    @GetMapping("/asdf")
    public ModelAndView asdf(ModelAndView modelAndView) {
        modelAndView.setViewName("asdf");

        return modelAndView;
    }

    @MessageMapping("/send")
    //@SendToUser(value = "/topic/send",broadcast = false)
    @SendTo("/topic/send")
    public SocketMessage send(SocketMessage msg) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msg.date = df.format(new Date());
        return msg;
    }

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/callback")
    public Object callback() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
        return "callback";
    }

    @SubscribeMapping("/topic/send")
    public SocketMessage sub() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SocketMessage msg = new SocketMessage();
        msg.message = "thank you for subscribe me~";
        msg.date = df.format(new Date());
        return msg;
    }

    @Autowired
    DataUtil util;


    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/client")
    public Object client() {
        List<Sensor> list = util.sensorMapper.getManyByGroupId(Long.valueOf(1));
        for (Sensor sensor : list) {
            sensor.setSensorGroup(null);
        }
        messagingTemplate.convertAndSend("/topic/client", list);
        return "client";
    }

    //@MessageMapping("/clientSend")
    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/clientMsg")
    public Object clientMsg() {
        Random random = new Random();
        List<SensorMsgTest> msgList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            SensorMsgTest test = new SensorMsgTest();
            test.id = i;
            test.msg = "id : " + i + " send: " + random.nextInt(100);
            msgList.add(test);
        }
        messagingTemplate.convertAndSend("/topic/clientMsg", msgList);
        return "clientMsg";

    }


}

class SensorMsgTest {
    public int id;
    public String msg;
}

class SocketMessage {
    public String message;
    public String date;
}