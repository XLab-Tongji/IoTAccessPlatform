package com.lab409.socket;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.*;
import com.lab409.socket.demoServer.model.*;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mapperTest {
    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SensorMsgMapper msgMapper;

    @Autowired
    SensorGroupMapper groupMapper;

    @Autowired
    GroupDetailMapper detailMapper;

    @Autowired
    DataUtil util;

    @Test
    public void insertUser() {
        userMapper.insert(new User(Long.valueOf(3),"dogtwo","123456"));
    }

    @Test
    public void insertGroup() {
        SensorGroup sensorGroup = new SensorGroup();
        sensorGroup.setCreateUser("dogtwo");
        sensorGroup.setName("group2");
        groupMapper.insert(sensorGroup);
    }

    @Test
    public void insertDetail() {
        GroupDetail detail = new GroupDetail();
        detail.setGroupId(Long.valueOf(2));
        detail.setSensorNum(Long.valueOf(2));
        detail.setType(SensorType.thunder);
        detailMapper.insert(detail);
        detail.setType(SensorType.humidity);
        detailMapper.insert(detail);
        detail.setType(SensorType.temperature);
        detailMapper.insert(detail);
        detail.setType(SensorType.pressure);
        detailMapper.insert(detail);
    }

    @Test
    public void insertSensor() {
        Sensor sensor = new Sensor();
        SensorGroup group = new SensorGroup();
        group.setId(Long.valueOf(3));
        sensor.setSensorGroup(group);
        sensor.setType(SensorType.thunder);
        sensor.setDescr("made in china");
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
        sensor.setType(SensorType.humidity);
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
        sensor.setType(SensorType.pressure);
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
        sensor.setType(SensorType.temperature);
        sensorMapper.insert(sensor);
        sensorMapper.insert(sensor);
    }


    @Test
    public void insertMsg() {
        SensorMsg msg = new SensorMsg();
        for (int i = 9; i < 17; i++) {
            msg.setSensorId(Long.valueOf(i));
            msg.setMsg("Hello World " + i + " times");
            msg.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            msgMapper.insert(msg);
            msg.setSendTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            msgMapper.insert(msg);
        }
    }

    @Test
    public void querySensor() {
        List<Sensor> sensors = sensorMapper.getManyByGroupId(Long.valueOf(1));
        for (Sensor sensor : sensors) {
            System.out.println(sensor);
        }
    }

    @Test
    public void queryAll() {
        List<SensorGroup> groups = groupMapper.getAll();
        for (SensorGroup g : groups) {
            System.out.println(g);
            for (GroupDetail detail : g.getGroupDetails()) {
                System.out.println(detail.getType() + " " + detail.getSensorNum());
            }
            for (Sensor sensor : g.getSensors()) {
                System.out.println(sensor);
            }
        }
    }
}
