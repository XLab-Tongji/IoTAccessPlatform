package com.lab409.socket;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.enums.SensorType;
import com.lab409.socket.demoServer.mapper.SensorMapper;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.netty.SocketServer;
import com.lab409.socket.demoServer.utils.ClientUtil;
import com.lab409.socket.demoServer.utils.client.SensorClientThread;
import com.lab409.socket.old.socket.client.ClientReceiveRunnable;
import com.lab409.socket.old.socket.client.ClientSendRunnable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


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
        /*ClientUtil util = context.getBean(ClientUtil.class);
        SensorMapper mapper = context.getBean(SensorMapper.class);
        List<Sensor> sensors = mapper.getManyByGroupId(Long.valueOf(1));
        util.createClients(sensors);
        */
        //this.openOneClient();

        /**/



    }
    public void openOneClient() {
        Sensor sensor = new Sensor();
        sensor.setId(Long.valueOf(1));
        sensor.setHost("localhost");
        sensor.setPort("8080");
        sensor.setState(SensorState.online);
        sensor.setType(SensorType.temperature);
        sensor.setChangedTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        sensor.setLatestMsg("tomorrow is well");
        sensor.setInterval(Long.valueOf(2000));
        SensorClientThread sensorClientThread = new SensorClientThread();
        sensorClientThread.setSensor(sensor);
        Thread thread = new Thread(sensorClientThread);
        thread.start();
    }
}