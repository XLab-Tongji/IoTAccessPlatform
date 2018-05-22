package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.ConfigDetailMapper;
import com.lab409.socket.demoServer.mapper.SensorConfigMapper;
import com.lab409.socket.demoServer.model.ConfigDetail;
import com.lab409.socket.demoServer.model.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigDetailMapperTest {
    @Autowired
    ConfigDetailMapper detailMapper;

    @Test
    public void insert() {
        ConfigDetail configDetail = new ConfigDetail();
        configDetail.setConfigId(Long.valueOf(4));
        configDetail.setType(SensorType.humidity);
        configDetail.setSensorNum(Long.valueOf(2));
        detailMapper.insert(configDetail);
    }

    @Test
    public void queryOne(){
        ConfigDetail detail = detailMapper.getOneByIdAndType(Long.valueOf(4),SensorType.thunder);
        System.out.println(detail.getSensorNum());
    }

    @Test
    public void queryMany() {
        List<ConfigDetail> details = detailMapper.getManyByConfigId(Long.valueOf(4));
        for (ConfigDetail detail : details) {
            System.out.println(detail.getConfigId() + " " + detail.getType()+ " "+ detail.getSensorNum());
        }
    }
}
