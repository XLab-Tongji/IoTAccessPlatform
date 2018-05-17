package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.entity.Sensor;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SensorMapper {
   @Select("SELECT * FROM sensors")
   List<Sensor> getAll();

   @Select("SELECT * FROM sensors WHERE id=#{id}")
   Sensor getOne(Long id);

   @Insert("INSERT INTO sensors(id, type, descr, host, port, msg, state) VALUES(#{id}, #{type}, #{descr}, #{host}, #{port}, #{msg}, #{state})")
   void insert(Sensor sensor);

   @Update("UPDATE sensors SET msg={msg}, state=#{state} WHERE id=#{id}")
    void update(Sensor sensor);

   @Delete("DELETE FROM sensors WHERE id=#{id}")
    void delete(Long id);
}
