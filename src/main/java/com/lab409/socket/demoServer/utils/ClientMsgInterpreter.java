package com.lab409.socket.demoServer.utils;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.Sensor;

import java.util.Date;

public class ClientMsgInterpreter {
    public static Sensor getSensorFromString(String msg) {
        String[] strs = msg.split(" ");
        if (strs.length < 6) return null;
        //Sensor sensor = new Sensor(Long.valueOf(strs[0]),Long.valueOf(1),SensorType.valueOf(strs[1]),strs[2],strs[3],strs[4],strs[5],strs[6],new Date());
        return new Sensor();
    }
}
