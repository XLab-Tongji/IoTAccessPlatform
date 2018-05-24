package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.model.SensorGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

//checked

public interface SensorGroupMapper {
    @Insert("insert into sensor_group(create_user) values(#{createUser})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(SensorGroup sensorGroup);

    @Select("select * from sensor_group where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "createUser", column = "create_user"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configDetails", column = "id", javaType = List.class,
            many = @Many(select = "com.lab409.socket.demoServer.mapper.GroupDetailMapper.getManyByConfigId")),
            @Result(property = "sensors", column = "id", javaType = List.class,
            many = @Many(select = "com.lab409.socket.demoServer.mapper.SensorMapper.getManyByConfigId"))
    })
    public SensorGroup getOneById(@Param("id") Long id);
}
