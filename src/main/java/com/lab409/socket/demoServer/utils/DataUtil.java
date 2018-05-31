package com.lab409.socket.demoServer.utils;

import com.lab409.socket.demoServer.mapper.*;
import com.lab409.socket.demoServer.model.SensorGroup;
import com.lab409.socket.demoServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataUtil {

    @Autowired
    public GroupDetailMapper detailMapper;

    @Autowired
    public SensorGroupMapper groupMapper;

    @Autowired
    SensorMsgMapper msgMapper;

    @Autowired
    SensorMapper sensorMapper;

    @Autowired
    UserMapper userMapper;
}
