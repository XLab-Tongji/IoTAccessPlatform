package com.lab409.socket.demoServer.utils.client.receiver;

import com.lab409.socket.demoServer.model.SensorMsg;
import com.lab409.socket.demoServer.utils.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@RabbitListener(queues = "storage")
@Component
public class StorageReceiver {
    private Logger logger = LoggerFactory.getLogger(StorageReceiver.class);

    private AtomicInteger num = new AtomicInteger(0);

    @Autowired
    DataUtil util;

    @RabbitHandler
    public void test(String str) {
        String[] strs = str.split("/");
        switch (strs.length) {
            case 2:
                SensorMsg msg = new SensorMsg(Long.valueOf(strs[0].substring(2)),
                        strs[1],
                        Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                util.msgMapper.insert(msg);
                break;
            case 3:
                break;
        }
        num.addAndGet(1);
        logger.info(str + " " + num.toString());
    }

}
