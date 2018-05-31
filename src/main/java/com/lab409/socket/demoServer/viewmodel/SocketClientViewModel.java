package com.lab409.socket.demoServer.viewmodel;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import com.lab409.socket.demoServer.model.SensorMsg;

import java.sql.Timestamp;
import java.util.List;

public class SocketClientViewModel extends Sensor {
    private Integer row_id;

    public SocketClientViewModel(Long id, SensorGroup sensorGroup, SensorType type, String descr, String host, String port, String latestMsg, String state, Timestamp changedTime, List<SensorMsg> sensorMsgs, Integer row_id) {
        super(id, sensorGroup, type, descr, host, port, latestMsg, state, changedTime, sensorMsgs);
        this.row_id = row_id;
    }

    public SocketClientViewModel(Sensor sensor, Integer row_id) {
        super(sensor);
        this.row_id = row_id;
    }
    public Integer getRow_id() {
        return row_id;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }

}
