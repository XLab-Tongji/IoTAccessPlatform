package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.model.SensorConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

//checked

public interface SensorConfigMapper {
    @Insert("insert into sensor_config(create_user, create_time) values(#{createUser}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(SensorConfig sensorConfig);

    @Select("select * from sensor_config where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "createUser", column = "create_user"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configDetails", column = "id", javaType = List.class,
            many = @Many(select = "com.lab409.socket.demoServer.mapper.ConfigDetailMapper.getManyByConfigId")),
            @Result(property = "sensors", column = "id", javaType = List.class,
            many = @Many(select = "com.lab409.socket.demoServer.mapper.SensorMapper.getManyByConfigId"))
    })
    public SensorConfig getOneById(@Param("id") Long id);
}
