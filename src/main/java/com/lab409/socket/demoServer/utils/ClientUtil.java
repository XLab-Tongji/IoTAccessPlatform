package com.lab409.socket.demoServer.utils;

import com.lab409.socket.demoServer.enums.SensorState;
import com.lab409.socket.demoServer.model.Sensor;
import com.lab409.socket.demoServer.utils.client.SensorClientThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton")
public class ClientUtil {
    private Long groupId = null;

    @Autowired
    DataUtil dataUtil;

    private Map<Long, SensorClientThread> repository = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(ClientUtil.class);

    public void createClients(Long groupId) {
        if (this.groupId == null || !this.groupId.equals(groupId)) {
            logger.info("clients of group " + groupId + " has been created");
            this.destroyClients();
            this.groupId = groupId;
            List<Sensor> sensors = dataUtil.sensorMapper.getManyByGroupId(groupId);
            for (Sensor sensor : sensors) {
                SensorClientThread sensorClientThread = new SensorClientThread();
                sensorClientThread.setSensor(sensor);
                Thread thread1 = new Thread(sensorClientThread);
                thread1.start();
                repository.put(sensor.getId(), sensorClientThread);
            }
        }
    }

    public void updateClientMsg(Long sensorId, Long interval, String msg) {
        SensorClientThread sensorClientThread = repository.get(sensorId);
        if (sensorClientThread != null) {
            Sensor sensor = sensorClientThread.getSensor();
            if (sensor != null) {
                sensor.setLatestMsg(msg);
                sensor.setInterval(interval);
                sensorClientThread.setSensor(sensor);
            }
        }
    }

    public void updateClientState(Long sensorId, SensorState sensorState) {
        SensorClientThread sensorClientThread = repository.get(sensorId);
        if(sensorClientThread!=null) {
            Sensor sensor = sensorClientThread.getSensor();
            if (sensor != null) sensor.setState(sensorState);
        }
    }

    public void destroyClients() {
        if (this.repository != null && !repository.isEmpty()) {
            for (SensorClientThread sensorClientThread : repository.values()) {
                sensorClientThread.destroy();
            }
            repository.clear();
        }

    }
}
