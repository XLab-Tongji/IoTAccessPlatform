package com.lab409.socket.demoServer.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class SensorGroup implements Serializable {

    private List<GroupDetail> groupDetails;

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

    public List<GroupDetail> getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(List<GroupDetail> groupDetails) {
        this.groupDetails = groupDetails;
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
