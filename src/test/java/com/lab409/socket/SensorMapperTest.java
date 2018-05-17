package com.lab409.socket;

import com.lab409.socket.demoServer.entity.Sensor;
import com.lab409.socket.demoServer.mapper.SensorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorMapperTest {
    @Autowired
    SensorMapper sensorMapper;

    @Test
    public void insert() {
        sensorMapper.insert(new Sensor(new Long(1),"temp","localhost","1234","hello","online","made in china"));
    }

    @Test
    public void queryAll() {
        List<Sensor> list = sensorMapper.getAll();
        System.out.println(list);
        /*for (Sensor sensor : list) {
            System.out.println(sensor);
        }*/
    }
}
