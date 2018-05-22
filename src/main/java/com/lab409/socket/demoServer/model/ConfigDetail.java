package com.lab409.socket.demoServer.model;

import com.lab409.socket.demoServer.enums.SensorType;

public class ConfigDetail {
    private Long configId;
    private SensorType type;
    private Long sensorNum;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public Long getSensorNum() {
        return sensorNum;
    }

    public void setSensorNum(Long sensorNum) {
        this.sensorNum = sensorNum;
    }
}
