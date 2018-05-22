package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.mapper.SensorConfigMapper;
import com.lab409.socket.demoServer.model.ConfigDetail;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorConfig;
import com.lab409.socket.demoServer.model.SensorMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorConfigMapperTest {
    @Autowired
    SensorConfigMapper sensorConfigMapper;

    @Test
    public void insert() {
        SensorConfig config = new SensorConfig();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(format.format(new java.util.Date()));
        config.setCreateTime(timestamp);
        config.setCreateUser("admin");
        sensorConfigMapper.insert(config);
    }

    @Test
    public void queryOne() {
        SensorConfig config = sensorConfigMapper.getOneById(Long.valueOf(4));
        List<ConfigDetail> details = config.getConfigDetails();
        List<Sensor> sensors = config.getSensors();
        System.out.println(config.getId() + " " +config.getCreateUser() +" "+ config.getCreateTime());
        for (ConfigDetail detail :details){
            System.out.println(detail.getConfigId()+" "+detail.getType()+" "+detail.getSensorNum());
        }
        for (Sensor sensor : sensors) {
            //System.out.println(sensor.getId() + " " + sensor.getType() + " " +sensor.getLatestMsg());
            for(SensorMsg msg : sensor.getSensorMsgs()) {
                System.out.println(msg.getSensorId() + " " + msg.getMsg() +" "+ msg.getSendTime());
            }
        }


    }

}
