package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.SensorGroupMapper;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    SensorGroupMapper groupMapper;


    @GetMapping("/test")
    public Sensor test() {
        Sensor sensor = new Sensor();
        sensor.setType(SensorType.humidity);
        sensor.setDescr("sensor test");
        sensor.setId(Long.valueOf(1));
        sensor.setHost("localhost");
        sensor.setState("online");
        return sensor;
    }

    @GetMapping("/msg")
    public MessageTest messageTest() {
        MessageTest messageTest = new MessageTest();
        messageTest.msg = "this is a message";
        messageTest.receiver = "client";
        messageTest.sender = "server";
        return messageTest;
    }

    @PostMapping void groupTest(@RequestBody SensorGroup group) {
        System.out.println(group);
    }

    @PostMapping("/addNewGroup")
    public SensorGroup addNewGroup(@RequestBody SensorGroup group) {
        group.setCreateTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        groupMapper.insert(group);
        return new SensorGroup();
    }

    @GetMapping("/getAllGroup")
    public List<SensorGroup> getAllGroup() {
        return groupMapper.getAll();
    }
}
class MessageTest implements Serializable {
    public String msg;
    public String sender;
    public String receiver;
}


