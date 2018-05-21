package com.lab409.socket.demoServer.utils;

import com.lab409.socket.demoServer.model.Sensor;

public class ClientMsgInterpreter {
    public static Sensor getSensorFromString(String msg) {
        String[] strs = msg.split(" ");
        if (strs.length < 6) return null;
        Sensor sensor = new Sensor(Integer.valueOf(strs[0]),strs[1],strs[2],strs[3],strs[4],strs[5],strs[6]);
        return sensor;
    }
}
