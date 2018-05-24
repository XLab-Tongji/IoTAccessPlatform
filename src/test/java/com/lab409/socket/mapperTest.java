package com.lab409.socket;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.*;
import com.lab409.socket.demoServer.model.GroupDetail;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import com.lab409.socket.demoServer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void insertUser() {
        userMapper.insert(new User(Long.valueOf(3),"dogtwo","123456"));
    }

    @Test
    public void insertGroup() {
        SensorGroup sensorGroup = new SensorGroup();
        sensorGroup.setCreateUser("admin");
        groupMapper.insert(sensorGroup);
    }

    @Test
    public void insertDetail() {
        GroupDetail detail = new GroupDetail();
        detail.setConfigId(Long.valueOf(1));
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
        group.setId(Long.valueOf(1));
        sensor.setSensorGroup(group);
        sensor.setType(SensorType.thunder);
        sensor.setDescr("made in china");
        sensorMapper.insert(sensor);
    }

    @Test
    public void queryAll() {
    }
}
