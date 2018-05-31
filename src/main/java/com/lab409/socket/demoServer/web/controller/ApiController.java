package com.lab409.socket.demoServer.web.controller;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.GroupDetailMapper;
import com.lab409.socket.demoServer.mapper.SensorGroupMapper;
import com.lab409.socket.demoServer.model.GroupDetail;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import com.lab409.socket.demoServer.model.SensorMsg;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    DataUtil dataUtil;

    @GetMapping("/test")
    public Sensor test() {
        Sensor sensor = new Sensor();
        sensor.setType(SensorType.humidity);
        sensor.setDescr("sensor test");
        sensor.setId(Long.valueOf(1));
        sensor.setHost("localhost");
        sensor.setState(SensorState.online);
        return sensor;
    }

    @PostMapping("/addNewGroup")
    public SensorGroup addNewGroup(@RequestBody SensorGroup group) {
        group.setCreateTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        dataUtil.groupMapper.insert(group);
        for (GroupDetail detail : group.getGroupDetails()) {
            detail.setGroupId(group.getId());
            dataUtil.detailMapper.insert(detail);
        }
        return group;
    }

    @Autowired
    SensorGroupMapper groupMapper;

    /**
     * 注意 : 避免 json 循环序列化问题
     * @return
     */

    @GetMapping("/getAllGroup")
    public SensorGroup getAllGroup() {
        /*SensorGroup sensorGroup = new SensorGroup();
        sensorGroup.setId(Long.valueOf(1));
        List<Sensor> sensors = new ArrayList<>();
        Sensor sensor = new Sensor();
        sensor.setId(Long.valueOf(1));
        sensor.setHost("localhost");
        sensor.setPort("8080");
        sensor.setState(SensorState.online);
        sensor.setType(SensorType.temperature);
        sensor.setChangedTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        sensor.setLatestMsg("tomorrow is well");
        List<SensorMsg> msgs = new ArrayList<>();
        SensorMsg msg = new SensorMsg();
        msg.setId(Long.valueOf(1));
        msg.setSensorId(Long.valueOf(1));
        msg.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        msg.setMsg("tomorrow is well");
        msgs.add(msg);
        msgs.add(msg);
        sensor.setSensorMsgs(msgs);
        sensors.add(sensor);
        sensor.setSensorGroup(sensorGroup);
        sensorGroup.setSensors(sensors);
        GroupDetail detail = new GroupDetail();
        detail.setGroupId(Long.valueOf(1));
        detail.setType(SensorType.temperature);
        detail.setSensorNum(Long.valueOf(1));
        List<GroupDetail> details = new ArrayList<>();
        details.add(detail);
        sensorGroup.setGroupDetails(details);*/


        SensorGroup sensorGroup = groupMapper.getOneById(Long.valueOf(1));
        for (Sensor sensor : sensorGroup.getSensors()) {
            sensor.setSensorGroup(null);    //不加这一行代码会导致json循环序列化问题
        }
        return sensorGroup;
    }



    @PostMapping("/postTest")
    public Msg postTest(@RequestBody Msg msg) {
        System.out.println(msg);
        Msg msg1 = new Msg();
        msg.sender = "server";
        msg.content = "i have received your message";
        return msg;
    }
}
class Msg {
    public String sender;
    public String content;

    @Override
    public String toString() {
        return sender + " : " + content;
    }
}


