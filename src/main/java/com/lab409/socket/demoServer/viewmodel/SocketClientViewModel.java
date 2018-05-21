package com.lab409.socket.demoServer.viewmodel;

import com.lab409.socket.demoServer.model.Sensor;

public class SocketClientViewModel extends Sensor {
    private Integer row_id;

    public SocketClientViewModel(Integer id, String type, String host, String port, String msg, String state, String descr, int row_id) {
        super(id, type, host, port, msg, state, descr);
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
