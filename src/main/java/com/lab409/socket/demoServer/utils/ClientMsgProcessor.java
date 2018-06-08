package com.lab409.socket.demoServer.utils;


import com.lab409.socket.demoServer.model.SensorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@EnableScheduling
@RabbitListener(queues = "clientMsg")
public class ClientMsgProcessor {

    private Logger logger = LoggerFactory.getLogger(ClientMsgProcessor.class);
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @RabbitHandler
    @SendTo("/topic/clientMsg")
    public Object test(String msg) {
        Random random = new Random();
        List<SensorMsg> msgList = new ArrayList<>();
        String[] strings = msg.split("/");
        if (strings.length == 2) {
            SensorMsg message = new SensorMsg();
            message.setMsg(strings[1] + " " + random.nextInt(100) + "%");
            message.setSensorId(Long.valueOf(strings[0].trim()));
            message.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            //logger.info(message.getSensorId() + "/"+ message.getMsg());
            msgList.add(message);
            messagingTemplate.convertAndSend("/topic/clientMsg", msgList);
        }
        else {
            System.out.println(msg);
            System.out.println(strings.length);
        }
        return "clientMsg";

    }
}