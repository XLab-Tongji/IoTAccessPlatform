package com.lab409.socket.demoServer.viewmodel;

import com.lab409.socket.demoServer.entity.Sensor;
public class SocketClientViewModel extends Sensor {
    private int row_id;

    public SocketClientViewModel(Long id, String type, String host, String port, String msg, String state, String descr, int row_id) {
        super(id, type, host, port, msg, state, descr);
        this.row_id = row_id;
    }

    public int getRow_id() {
        return row_id;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }

}
