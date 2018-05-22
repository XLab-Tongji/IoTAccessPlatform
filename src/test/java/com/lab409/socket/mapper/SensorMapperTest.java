package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.SensorMapper;
import com.lab409.socket.demoServer.model.ConfigDetail;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorConfig;
import com.lab409.socket.demoServer.model.SensorMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SensorMapperTest {
    @Autowired
    SensorMapper sensorMapper;

    @Test
    public void insert() {
        Sensor sensor = new Sensor();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sensor.setLatestMsg("tomorrow has thunder");
        sensor.setChangedTime(Timestamp.valueOf(format.format(new Date())));
        sensor.setType(SensorType.humidity);
        SensorConfig config = new SensorConfig();
        config.setId(Long.valueOf(4));
        sensor.setSensorConfig(config);
        sensorMapper.insert(sensor);
    }

    @Test
    public void queryOne() {
        Sensor sensor = sensorMapper.getOneById(Long.valueOf(9));
        System.out.println(sensor.getId() + " " + sensor.getType() + " " +sensor.getLatestMsg());
        for(SensorMsg msg : sensor.getSensorMsgs()) {
            System.out.println(msg.getSensorId() + " " + msg.getMsg() +" "+ msg.getSendTime());
        }
    }

    @Test
    public void queryMany() {
        List<Sensor> sensors = sensorMapper.getManyByConfigId(Long.valueOf(4));
        for (Sensor sensor : sensors) {
            System.out.println(sensor.getId() + " " + sensor.getType() + " " +sensor.getLatestMsg());
            for(SensorMsg msg : sensor.getSensorMsgs()) {
                System.out.println(msg.getSensorId() + " " + msg.getMsg() +" "+ msg.getSendTime());
            }
        }
    }
}
