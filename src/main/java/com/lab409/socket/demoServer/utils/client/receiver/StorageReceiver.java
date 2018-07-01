package com.lab409.socket.demoServer.utils.client.receiver;

import com.datastax.driver.core.Session;
import com.lab409.socket.demoServer.model.SensorMsg;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLSyntaxErrorException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RabbitListener(queues = "storage")
@Component
public class StorageReceiver {
    private Logger logger = LoggerFactory.getLogger(StorageReceiver.class);

    private AtomicInteger num = new AtomicInteger(0);

    @Autowired
    DataUtil util;


    @Autowired
    @Qualifier("cassadra_session")
    Session session;

    @RabbitHandler
    public void storage(String str) {
        String[] strs = str.split("/");
        switch (strs.length) {
            case 2:
                try{
                    SensorMsg msg = new SensorMsg(Long.valueOf(strs[0].substring(2)),
                            strs[1],
                            Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                    util.msgMapper.insert(msg);
                    insert(msg);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

                break;
            case 3:
                break;
        }
        num.addAndGet(1);
        logger.info(str + " " + num.toString());
    }

    private void insert(SensorMsg msg) {
        StringBuilder builder = new StringBuilder();
        String[] msgs = msg.getMsg().split(" ");
        builder.append("INSERT INTO terminal (terminal_description, peak, lasting_time, polarity, date, unit1, unit2)");
        builder.append(" VALUES(\'");
        builder.append("9");
        builder.append("#雷电分析仪");
        builder.append("\',");
        if (msgs.length < 3) {
            System.out.println("length is too short");
            builder.append("\'invaild\'");
            builder.append(",");
            builder.append("\'invaild\'");
            builder.append(",");
            builder.append("\'invaild\'");
            builder.append("\'");
        }else {
            System.out.println("length is enough");
            builder.append(new Random().nextInt(80)+30);
            builder.append(",");
            builder.append(msg.getMsg().split(" ")[1]);
            builder.append(",\'");
            builder.append(msg.getMsg().split(" ")[2]);
            builder.append("\',\'");
        }
        builder.append(msg.getSendTime());
        builder.append("\',\'");
        builder.append("千安");
        builder.append("\',\'");
        builder.append("毫秒");
        builder.append("\');");
        System.out.println(builder.toString());
        session.execute(builder.toString());
    }
}
