package com.lab409.socket;

import com.lab409.socket.demoServer.mapper.UserMapper;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.mapper.SensorMapper;
import com.lab409.socket.demoServer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mapperTest {
    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    UserMapper userMapper;


    @Test
    public void insert() {
        userMapper.insert(new User(Long.valueOf(3),"dogtwo","123456"));
    }

    @Test
    public void queryAll() {
        //List<Sensor> list = sensorMapper.getAll();
        //System.out.println(list);
        /*for (Sensor sensor : list) {
            System.out.println(sensor);
        }*/
    }
}
