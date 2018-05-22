package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.ConfigDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

//checked

public interface ConfigDetailMapper {
    @Insert("insert into config_detail(id, type, num) " +
            "values(#{configId}, #{type}, #{sensorNum})")
    public void insert(ConfigDetail configDetail);

    @Select("select * from config_detail where id = #{id}")
    @Results({
            @Result(property = "configId", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "sensorNum", column = "num")
    })
    public List<ConfigDetail> getManyByConfigId(@Param("id") Long id);

    @Select("select  * from config_detail where id = #{id} and type = #{type}")
    @Results({
            @Result(property = "configId", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "sensorNum", column = "num")
    })
    public  ConfigDetail getOneByIdAndType(@Param("id") Long id, @Param("type") SensorType type);

}
