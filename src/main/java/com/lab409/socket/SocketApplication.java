package com.lab409.socket;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.netty.SocketServer;
import com.lab409.socket.demoServer.utils.client.SensorClientThread;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan("com.lab409.socket.demoServer.mapper")
@ServletComponentScan
public class SocketApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SocketApplication.class, args);
        SocketServer server = context.getBean(SocketServer.class);
        server.start();
    }
}