package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorMsg;
import org.apache.ibatis.annotations.*;

import java.util.List;

//checked

public interface SensorMsgMapper {
    @Insert("insert into sensor_msg(sensor_id, msg, time) " +
            "values(#{sensorId}, #{msg}, #{sendTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(SensorMsg sensorMsg);


    @Select("select * from sensor_msg where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "sensorId", column = "sensor_id"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sendTime", column = "time"),
    })
    public SensorMsg getOneById(@Param("id") Long id);


    @Select("select * from sensor_msg where sensor_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "sensorId", column = "sensor_id"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sendTime", column = "time")
    })
    public List<SensorMsg> getManyBySensorId(@Param("id") Long id);

}