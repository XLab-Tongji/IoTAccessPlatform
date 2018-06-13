package com.lab409.socket;

import com.lab409.socket.demoServer.utils.ClientUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientUtilTest {
    @Autowired
    ClientUtil util;
/*
    //@Autowired
   // SensorMapper sensorMapper;
    @Test
    public void test() {
        List<Sensor> sensors = sensorMapper.getManyByGroupId(Long.valueOf(1));
        util.createClients(sensors);
    }*/
}
