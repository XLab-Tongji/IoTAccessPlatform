package com.lab409.socket.demoServer.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class SensorConfig implements Serializable {

    private List<ConfigDetail> configDetails;

    public  Long id;

    private Timestamp createTime;

    private String createUser;

    private List<Sensor> sensors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ConfigDetail> getConfigDetails() {
        return configDetails;
    }

    public void setConfigDetails(List<ConfigDetail> configDetails) {
        this.configDetails = configDetails;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
