package com.lab409.socket.demoServer.web.controller;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.GroupDetail;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import com.lab409.socket.demoServer.utils.ClientUtil;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    DataUtil dataUtil;

    @Autowired
    ClientUtil clientUtil;

    @Autowired
    AmqpTemplate rabbitTemplate;

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
        Sensor sensor = new Sensor();
        sensor.setSensorGroup(group);
        sensor.setChangedTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        for (GroupDetail detail : group.getGroupDetails()) {
            detail.setGroupId(group.getId());
            dataUtil.detailMapper.insert(detail);
            for (Integer i = 0; i < detail.getSensorNum(); i++) {
                sensor.setType(detail.getType());
                dataUtil.sensorMapper.simplyInsert(sensor);
            }
        }
        return group;
    }

    /**
     * 注意 : 避免 json 循环序列化问题
     * @return
     */

    @GetMapping("/getAllGroup")
    public List<SensorGroup> getAllGroup() {
        List<SensorGroup> groups = dataUtil.groupMapper.getAll();
        for(SensorGroup group : groups ) {
            for (Sensor sensor : group.getSensors()) {
                sensor.setSensorGroup(null);    //不加这一行代码会导致json循环序列化问题
            }
        }
        return groups;
    }

    @GetMapping("/getSensorsDividedByType")
    public Map<String,List<Sensor>> getSensorsDividedByType(Long id) {
        //后台创建client线程
        rabbitTemplate.convertAndSend("order","create/" + id.toString());
        Map<String,List<Sensor>> map = new HashMap<>();
        for (SensorType type : SensorType.values()) {
            List<Sensor> sensors = dataUtil.sensorMapper.getManyByGroupIdAndType(Long.valueOf(id),type);
            for(Sensor sensor : sensors)
                sensor.setSensorGroup(null);
            map.put(type.name(), sensors);
        }
        return map;
    }

    @GetMapping("/getSensors")
    public List<Sensor> getSensors(Integer id) {
        List<Sensor> sensors = dataUtil.sensorMapper.getManyByGroupId(Long.valueOf(id));
        for(Sensor sensor : sensors) {
            sensor.setSensorGroup(null);
        }
        return sensors;
    }

    @PostMapping("/changeSensorState")
    public void changeSensorState(Integer id, boolean active) {

    }

}