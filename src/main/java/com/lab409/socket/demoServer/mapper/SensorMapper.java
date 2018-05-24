package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.model.SensorGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

// checked

public interface SensorMapper {

    @Select("select * from sensor where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "type", column = "type", javaType = SensorType.class),
            @Result(property = "host", column = "host"),
            @Result(property = "port", column = "port"),
            @Result(property = "state", column = "state", javaType = SensorState.class),
            @Result(property = "descr", column = "descr"),
            @Result(property = "latestMsg", column = "msg"),
            @Result(property = "changedTime", column = "time"),
            @Result(property = "sensorGroup", column = "group_id", javaType = SensorGroup.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorGroupMapper.getOneById")),
            @Result(property = "sensorMsgs", column = "id", javaType = List.class,
                    many = @Many(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getManyBySensorId"))
    })
    public Sensor getOneById(@Param("id") Long id);

    @Select("select * from sensor where group_id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "type", column = "type", javaType = SensorType.class),
            @Result(property = "host", column = "host"),
            @Result(property = "port", column = "port"),
            @Result(property = "state", column = "state", javaType = SensorState.class),
            @Result(property = "descr", column = "descr"),
            @Result(property = "latestMsg", column = "msg"),
            @Result(property = "changedTime", column = "time"),
            @Result(property = "sensorGroup", column = "group_id", javaType = SensorGroup.class,
                    one = @One(select = "com.lab409.socket.demoServer.mapper.SensorGroupMapper.getOneById")),
            @Result(property = "sensorMsgs", column = "id", javaType = List.class,
                    many = @Many(select = "com.lab409.socket.demoServer.mapper.SensorMsgMapper.getManyBySensorId"))

    })
    public List<Sensor> getManyByConfigId(@Param("id") Long id);


    @Insert("INSERT INTO sensor(config_id, type, descr ,host, port, msg, state, time) " +
            "VALUES(#{sensorConfig.id}, #{type}, #{descr}, #{host}, #{port}, #{latestMsg}, #{state}, #{changedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Sensor sensor);

    @Update("update sensor set type=#{type}, descr=#{descr}, host=#{host}," +
            "port=#{port}, state=#{state}, msg=#{latestMsg} WHERE id=#{id}")
    void update(Sensor sensor);

    @Delete("deleted from sensor where id=#{id}")
    void delete(Long id);
}
