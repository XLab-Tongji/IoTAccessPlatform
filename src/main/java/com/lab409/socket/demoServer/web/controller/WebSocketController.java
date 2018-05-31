package com.lab409.socket.demoServer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
class SocketMessage {

    public String message;

    public String date;

}