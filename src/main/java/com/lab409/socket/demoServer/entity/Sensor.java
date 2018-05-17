package com.lab409.socket.demoServer.entity;

import java.io.Serializable;

public class Sensor implements Serializable {
    private Long id;
    private String type;
    private String host;
    private String port;
    private String msg;
    private String state;
    private String descr;

    public Sensor(Long id, String type, String host, String port, String msg, String state, String descr) {
        this.id = id;
        this.type = type;
        this.host = host;
        this.port = port;
        this.msg = msg;
        this.state = state;
        this.descr = descr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "id:" + id + " type:" + type + " descr:" + descr + " host:" + host + " port" + port + " msg" + msg + " state" + state;
    }
}
